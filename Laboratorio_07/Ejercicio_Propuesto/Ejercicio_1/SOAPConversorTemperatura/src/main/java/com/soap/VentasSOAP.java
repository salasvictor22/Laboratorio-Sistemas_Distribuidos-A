package com.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "com.soap.VentasSOAPInterface")
public class VentasSOAP implements VentasSOAPInterface {

    @Override
    @WebMethod
    public String registrarVenta(String producto, int cantidad) {

        return "Venta registrada: "
                + cantidad
                + " unidad(es) de "
                + producto;
    }

    @Override
    @WebMethod
    public double calcularTotal(double precio, int cantidad) {

        return precio * cantidad;
    }
}