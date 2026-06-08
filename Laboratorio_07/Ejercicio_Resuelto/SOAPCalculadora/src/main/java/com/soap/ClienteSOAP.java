package com.soap;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class ClienteSOAP {

    public static void main(String[] args) throws Exception {

        URL url =
            new URL("http://localhost:8080/calculadora?wsdl");

        QName qname =
            new QName(
                "http://soap.com/",
                "CalculadoraSOAPService"
            );

        Service service =
            Service.create(url, qname);

        CalculadoraSOAPInterface calc =
            service.getPort(CalculadoraSOAPInterface.class);

        int resultado = calc.sumar(10, 20);

        System.out.println("Resultado: " + resultado);
    }
}