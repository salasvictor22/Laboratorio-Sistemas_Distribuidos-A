import java.net.*;
import java.io.*;
import java.util.*;

// The Client that can be run as a console
public class Client {

    // Notification
    private String notif = " *** ";

    // For I/O
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private Socket socket;

    // Server and username
    private String server, username;

    // Port
    private int port;

    // Constructor
    Client(String server, int port, String username) {
        this.server = server;
        this.port = port;
        this.username = username;
    }

    // Start the client
    public boolean start() {

        // Try to connect to the server
        try {
            socket = new Socket(server, port);
        } catch (Exception ec) {
            display("Error connecting to server: " + ec);
            return false;
        }

        String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
        display(msg);

        // Create data streams
        try {
            sInput = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException eIO) {
            display("Exception creating new Input/output Streams: " + eIO);
            return false;
        }

        // Thread to listen from server
        new ListenFromServer().start();

        // Send username
        try {
            sOutput.writeObject(username);
        } catch (IOException eIO) {
            display("Exception doing login : " + eIO);
            disconnect();
            return false;
        }

        return true;
    }

    // Display message
    private void display(String msg) {
        System.out.println(msg);
    }

    // Send message to server
    void sendMessage(ChatMessage msg) {
        try {
            sOutput.writeObject(msg);
        } catch (IOException e) {
            display("Exception writing to server: " + e);
        }
    }

    // Disconnect
    private void disconnect() {

        try {
            if (sInput != null)
                sInput.close();
        } catch (Exception e) {
        }

        try {
            if (sOutput != null)
                sOutput.close();
        } catch (Exception e) {
        }

        try {
            if (socket != null)
                socket.close();
        } catch (Exception e) {
        }
    }

    // Main
    public static void main(String[] args) {

        int portNumber = 1500;
        String serverAddress = "localhost";
        String userName;

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the username: ");
        userName = scan.nextLine();

        // Create client object
        Client client = new Client(serverAddress, portNumber, userName);

        // Start client
        if (!client.start())
            return;

        System.out.println("\nHello.! Welcome to the chatroom.");
        System.out.println("Instructions:");
        System.out.println("1. Type a message to send to all users");
        System.out.println("2. Type '@username message' for private message");
        System.out.println("3. Type 'WHOISIN' to see connected users");
        System.out.println("4. Type 'LOGOUT' to exit");

        // Infinite loop for messages
        while (true) {

            System.out.print("> ");

            String msg = scan.nextLine();

            // Logout
            if (msg.equalsIgnoreCase("LOGOUT")) {
                client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
                break;
            }

            // Show connected users
            else if (msg.equalsIgnoreCase("WHOISIN")) {
                client.sendMessage(new ChatMessage(ChatMessage.WHOISIN, ""));
            }

            // Normal message
            else {
                client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, msg));
            }
        }

        scan.close();

        // Disconnect
        client.disconnect();
    }

    /*
     * Class that waits for messages from the server
     */
    class ListenFromServer extends Thread {

        public void run() {

            while (true) {

                try {

                    String msg = (String) sInput.readObject();

                    System.out.println(msg);
                    System.out.print("> ");

                } catch (IOException e) {

                    display(notif + "Server has closed the connection: " + e + notif);
                    break;

                } catch (ClassNotFoundException e2) {
                }
            }
        }
    }
}