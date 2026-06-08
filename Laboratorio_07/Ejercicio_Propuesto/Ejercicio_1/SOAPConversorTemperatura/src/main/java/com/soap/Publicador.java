package com.soap;

import javax.xml.ws.Endpoint;

public class Publicador {

    public static void main(String[] args) {

        Endpoint.publish(
            "http://localhost:8080/conversor",
            new ConversorSOAP()
        );

        System.out.println("Servicio Conversor SOAP activo");
    }
}