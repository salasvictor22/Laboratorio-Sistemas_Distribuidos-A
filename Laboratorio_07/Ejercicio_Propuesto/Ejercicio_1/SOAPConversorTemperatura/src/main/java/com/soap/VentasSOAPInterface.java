package com.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface VentasSOAPInterface {

    @WebMethod
    String registrarVenta(String producto, int cantidad);

    @WebMethod
    double calcularTotal(double precio, int cantidad);
}