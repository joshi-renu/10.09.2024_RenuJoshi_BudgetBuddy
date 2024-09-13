package com.budgetbuddy.cloud.app.dto;

import com.budgetbuddy.cloud.app.entity.BudgetPlan;
import com.budgetbuddy.cloud.app.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class ExpenseResponse {

    private String description;

    private Double amount;

    private Date date;

    private User userId;

    private BudgetPlan budgetId;
}
