package com.budgetbuddy.cloud.app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExpenseSummaryDTO {
    private int expenseId;

    private String description;

    private Double amount;

    private Date date;

}
