package com.exam.in.examsystem.exception;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(){}
    public  InvalidCredentialsException(String msg){
        super(msg);
    }
}
