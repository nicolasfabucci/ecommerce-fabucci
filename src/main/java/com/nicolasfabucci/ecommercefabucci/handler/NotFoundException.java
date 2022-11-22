package com.nicolasfabucci.ecommercefabucci.handler;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
