import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

// Server class
public class Server {

    // Unique ID for each client
    private static int uniqueId;

    // List of connected clients
    private ArrayList<ClientThread> al;

    // Date format
    private SimpleDateFormat sdf;

    // Port number
    private int port;

    // Server running state
    private boolean keepGoing;

    // Notification
    private String notif = " *** ";

    // Constructor
    public Server(int port) {

        this.port = port;

        sdf = new SimpleDateFormat("HH:mm:ss");

        al = new ArrayList<ClientThread>();
    }

    // Start server
    public void start() {

        keepGoing = true;

        try {

            // Create server socket
            ServerSocket serverSocket = new ServerSocket(port);

            // Infinite loop
            while (keepGoing) {

                display("Server waiting for Clients on port " + port + ".");

                // Accept connection
                Socket socket = serverSocket.accept();

                if (!keepGoing)
                    break;

                // Create new client thread
                ClientThread t = new ClientThread(socket);

                al.add(t);

                t.start();
            }

            // Close server
            try {

                serverSocket.close();

                for (int i = 0; i < al.size(); ++i) {

                    ClientThread tc = al.get(i);

                    try {
                        tc.sInput.close();
                        tc.sOutput.close();
                        tc.socket.close();
                    } catch (IOException ioE) {
                    }
                }

            } catch (Exception e) {
                display("Exception closing the server and clients: " + e);
            }

        } catch (IOException e) {

            String msg = sdf.format(new Date()) + " Exception on new ServerSocket: " + e;

            display(msg);
        }
    }

    // Stop server
    protected void stop() {

        keepGoing = false;

        try {
            new Socket("localhost", port);
        } catch (Exception e) {
        }
    }

    // Display message
    private void display(String msg) {

        String time = sdf.format(new Date()) + " " + msg;

        System.out.println(time);
    }

    // Broadcast message
    private synchronized boolean broadcast(String message) {

        String time = sdf.format(new Date());

        String[] w = message.split(" ", 3);

        boolean isPrivate = false;

        if (w.length > 1 && w[1].charAt(0) == '@')
            isPrivate = true;

        // Private message
        if (isPrivate) {

            String toCheck = w[1].substring(1);

            message = w[0] + " " + w[2];

            String messageLf = time + " " + message + "\n";

            boolean found = false;

            for (int y = al.size(); --y >= 0;) {

                ClientThread ct1 = al.get(y);

                String check = ct1.username;

                if (check.equals(toCheck)) {

                    if (!ct1.writeMsg(messageLf)) {

                        al.remove(y);

                        display("Disconnected Client " + ct1.username + " removed from list.");
                    }

                    found = true;
                    break;
                }
            }

            if (!found)
                return false;
        }

        // Broadcast message
        else {

            String messageLf = time + " " + message + "\n";

            System.out.print(messageLf);

            for (int i = al.size(); --i >= 0;) {

                ClientThread ct = al.get(i);

                if (!ct.writeMsg(messageLf)) {

                    al.remove(i);

                    display("Disconnected Client " + ct.username + " removed from list.");
                }
            }
        }

        return true;
    }

    // Remove client
    synchronized void remove(int id) {

        String disconnectedClient = "";

        for (int i = 0; i < al.size(); ++i) {

            ClientThread ct = al.get(i);

            if (ct.id == id) {

                disconnectedClient = ct.username;

                al.remove(i);

                break;
            }
        }

        broadcast(notif + disconnectedClient + " has left the chat room." + notif);
    }

    // Main
    public static void main(String[] args) {

        int portNumber = 1500;

        Server server = new Server(portNumber);

        server.start();
    }

    /*
     * One thread for each client
     */
    class ClientThread extends Thread {

        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;

        int id;

        String username;

        ChatMessage cm;

        String date;

        // Constructor
        ClientThread(Socket socket) {

            id = ++uniqueId;

            this.socket = socket;

            System.out.println("Thread trying to create Object Input/Output Streams");

            try {

                sOutput = new ObjectOutputStream(socket.getOutputStream());

                sInput = new ObjectInputStream(socket.getInputStream());

                username = (String) sInput.readObject();

                broadcast(notif + username + " has joined the chat room." + notif);

            } catch (IOException e) {

                display("Exception creating new Input/output Streams: " + e);
                return;

            } catch (ClassNotFoundException e) {
            }

            date = new Date().toString();
        }

        // Run thread
        public void run() {

            boolean keepGoing = true;

            while (keepGoing) {

                try {

                    cm = (ChatMessage) sInput.readObject();

                } catch (IOException e) {

                    display(username + " Exception reading Streams: " + e);
                    break;

                } catch (ClassNotFoundException e2) {
                    break;
                }

                String message = cm.getMessage();

                switch (cm.getType()) {

                    case ChatMessage.MESSAGE:

                        boolean confirmation = broadcast(username + ": " + message);

                        if (!confirmation) {

                            String msg = notif + "Sorry. No such user exists." + notif;

                            writeMsg(msg);
                        }

                        break;

                    case ChatMessage.LOGOUT:

                        display(username + " disconnected with a LOGOUT message.");

                        keepGoing = false;

                        break;

                    case ChatMessage.WHOISIN:

                        writeMsg("List of the users connected at " + sdf.format(new Date()) + "\n");

                        for (int i = 0; i < al.size(); ++i) {

                            ClientThread ct = al.get(i);

                            writeMsg((i + 1) + ") " + ct.username + " since " + ct.date);
                        }

                        break;
                }
            }

            remove(id);

            close();
        }

        // Close everything
        private void close() {

            try {
                if (sOutput != null)
                    sOutput.close();
            } catch (Exception e) {
            }

            try {
                if (sInput != null)
                    sInput.close();
            } catch (Exception e) {
            }

            try {
                if (socket != null)
                    socket.close();
            } catch (Exception e) {
            }
        }

        // Write message to client
        private boolean writeMsg(String msg) {

            if (!socket.isConnected()) {

                close();

                return false;
            }

            try {

                sOutput.writeObject(msg);

            } catch (IOException e) {

                display(notif + "Error sending message to " + username + notif);

                display(e.toString());
            }

            return true;
        }
    }
}