package com.shreyas.healthcare.exception;

public class ResourceAlreadyExistsException extends RuntimeException{
    ResourceAlreadyExistsException(String message){
        super (message);
    }
}
