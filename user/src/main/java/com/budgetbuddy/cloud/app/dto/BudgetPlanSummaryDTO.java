package com.budgetbuddy.cloud.app.dto;

import lombok.Data;

@Data
public class BudgetPlanSummaryDTO {
    private String category;

    private Double budgetLimit;

    private Double currBudget;

    private String monthYear;
}
