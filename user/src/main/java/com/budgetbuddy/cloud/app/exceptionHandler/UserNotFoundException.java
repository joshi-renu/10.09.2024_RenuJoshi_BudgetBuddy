package com.budgetbuddy.cloud.app.exceptionHandler;

import java.io.Serial;


public class UserNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID= 1356176878781267L;
    public UserNotFoundException(String msg){
        super(msg);
    }
}
