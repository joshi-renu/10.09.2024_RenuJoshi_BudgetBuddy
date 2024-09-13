package com.budgetbuddy.cloud.app.dto;

import com.budgetbuddy.cloud.app.entity.BudgetPlan;
import com.budgetbuddy.cloud.app.entity.Expense;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private int userId;

    private String userName;

    private String email;

    private List<Expense> expenses;

    private List<BudgetPlan> budgetPlans;

    private List<FinancialGoalSummaryDTO> financialGoals;
}
