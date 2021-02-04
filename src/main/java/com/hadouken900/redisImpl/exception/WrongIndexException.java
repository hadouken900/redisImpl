package com.hadouken900.redisImpl.exception;

public class WrongIndexException extends RuntimeException{

    public WrongIndexException(String message) {
        super(message);
    }
}
