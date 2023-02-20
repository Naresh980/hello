package com.ltp.springbootcrudexample.Exceptions;

public class ItemNotFoundException extends Exception{
    public ItemNotFoundException(String message){
        super(message);
    }
}
