package com.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ConversorSOAPInterface {

    @WebMethod
    double cToF(double c);

    @WebMethod
    double fToC(double f);
}