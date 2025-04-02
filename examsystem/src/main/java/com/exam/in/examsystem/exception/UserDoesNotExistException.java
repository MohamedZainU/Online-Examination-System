package com.exam.in.examsystem.exception;

public class UserDoesNotExistException extends  RuntimeException{

    public UserDoesNotExistException(){}

    public UserDoesNotExistException(String msg){
        super(msg);
    }

}
