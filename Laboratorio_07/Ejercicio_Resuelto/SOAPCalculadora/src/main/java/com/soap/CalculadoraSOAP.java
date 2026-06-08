package com.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "com.soap.CalculadoraSOAPInterface")
public class CalculadoraSOAP implements CalculadoraSOAPInterface {

    @Override
    @WebMethod
    public int sumar(int a, int b) {
        return a + b;
    }
}