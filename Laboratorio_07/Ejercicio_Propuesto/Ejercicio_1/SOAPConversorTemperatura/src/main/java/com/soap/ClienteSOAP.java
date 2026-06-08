package com.soap;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class ClienteSOAP {

    public static void main(String[] args) throws Exception {

        URL url =
            new URL("http://localhost:8080/conversor?wsdl");

        QName qname =
            new QName(
                "http://soap.com/",
                "ConversorSOAPService"
            );

        Service service =
            Service.create(url, qname);

        ConversorSOAPInterface conv =
            service.getPort(ConversorSOAPInterface.class);

        System.out.println("30 °C = "
                + conv.cToF(30)
                + " °F");

        System.out.println("86 °F = "
                + conv.fToC(86)
                + " °C");
    }
}