package com.budgetbuddy.cloud.app.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class FinancialGoalSummaryDTO {
    private int goalId;

    private Double targetAmount;

    private Double currAmount;

    private String monthYear;
}
