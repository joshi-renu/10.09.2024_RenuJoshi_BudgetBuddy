package com.budgetbuddy.cloud.app.dto;

import com.budgetbuddy.cloud.app.entity.Expense;
import com.budgetbuddy.cloud.app.entity.User;
import lombok.Data;
import java.util.List;

@Data
public class BudgetResponse {

    private String category;

    private Double budgetLimit;

    private Double currBudget;

    private String monthYear;

    private User user;

    private List<ExpenseSummaryDTO> expenses;
}
