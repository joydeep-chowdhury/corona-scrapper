package com.joydeep.selenium.corona.scrapper.exceptions;

public class KeyNotFoundException extends RuntimeException{
    private  static String exceptionMessage = "Key Not Found";
    
    public KeyNotFoundException(String exceptionMessage){
        super(exceptionMessage);
        this.exceptionMessage=exceptionMessage;
    }

    @Override
    public String toString() {
        return "KeyNotFoundException: "+exceptionMessage;
    }
}
