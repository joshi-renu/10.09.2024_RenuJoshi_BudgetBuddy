package com.budgetbuddy.cloud.app.exceptionHandler;

import java.io.Serial;


public class ExpenseNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID= 1356176878781267L;
    public ExpenseNotFoundException(String msg){
        super(msg);
    }
}
