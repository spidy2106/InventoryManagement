package com.Inventory.Management.Exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message){
        super(message);
    }
}
