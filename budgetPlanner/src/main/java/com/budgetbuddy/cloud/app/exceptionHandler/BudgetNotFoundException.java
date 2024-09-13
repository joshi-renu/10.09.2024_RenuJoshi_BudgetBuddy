package com.budgetbuddy.cloud.app.exceptionHandler;

import java.io.Serial;


public class BudgetNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID= 1356176878781267L;
    public BudgetNotFoundException(String msg){
        super(msg);
    }
}
