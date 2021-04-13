package com.joydeep.selenium.corona.scrapper.exceptions;

public class ReportGenerationFailedException extends RuntimeException{
    private  static String exceptionMessage = "Key Not Found";

    public ReportGenerationFailedException(String exceptionMessage){
        super(exceptionMessage);
        this.exceptionMessage=exceptionMessage;
    }

    @Override
    public String toString() {
        return "ReportGenerationFailedException: "+exceptionMessage;
    }
}
