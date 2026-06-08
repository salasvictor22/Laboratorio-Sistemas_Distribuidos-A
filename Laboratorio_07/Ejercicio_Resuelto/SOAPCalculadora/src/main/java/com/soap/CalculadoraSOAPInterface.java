package com.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface CalculadoraSOAPInterface {

    @WebMethod
    int sumar(int a, int b);
}