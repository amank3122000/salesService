package com.medistock.salesService.exception;

public class SalesNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public SalesNotFoundException(String msg) {
        super(msg);
    }
}
