package com.example.faptebune.domain.exceptions;

public class EmptyStringException extends Exception{
    public EmptyStringException(String message){
        super(message);
    }
}