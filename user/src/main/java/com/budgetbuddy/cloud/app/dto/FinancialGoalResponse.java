package com.budgetbuddy.cloud.app.dto;

import com.budgetbuddy.cloud.app.entity.FinancialGoal;
import com.budgetbuddy.cloud.app.entity.User;
import lombok.Data;

@Data
public class FinancialGoalResponse {

    private Double targetAmount;
    private Double currAmount;
    private String monthYear;
    private User user;

}
