package com.nicolasfabucci.ecommercefabucci.handler;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiRestException extends Exception {
    private String code;
    public ApiRestException(String code, String message){
        super(message);
        this.code = code;
    }
}
