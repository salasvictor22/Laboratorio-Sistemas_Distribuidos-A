package com.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(
    endpointInterface = "com.soap.ConversorSOAPInterface"
)
public class ConversorSOAP implements ConversorSOAPInterface {

    @Override
    @WebMethod
    public double cToF(double c) {
        return (c * 9 / 5) + 32;
    }

    @Override
    @WebMethod
    public double fToC(double f) {
        return (f - 32) * 5 / 9;
    }
}
