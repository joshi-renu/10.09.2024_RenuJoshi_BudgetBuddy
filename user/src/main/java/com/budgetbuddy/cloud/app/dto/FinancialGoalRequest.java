package com.budgetbuddy.cloud.app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class FinancialGoalRequest {

    @NotNull(message = "Target Amount is required and should not be null.")
    private Double targetAmount;

    @NotNull(message = "Current Amount is required and should not be null.")
    private Double currAmount;

    @NotNull(message = "monthYear must be in <Sept2024> format and not null")
    private String monthYear;

}
