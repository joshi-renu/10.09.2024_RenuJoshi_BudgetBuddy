package com.budgetbuddy.cloud.app.dto;

import com.budgetbuddy.cloud.app.entity.Expense;
import com.budgetbuddy.cloud.app.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;

@Data
public class BudgetRequest {

    @NotNull(message = "Category name is required and should not be null.")
    @NotEmpty(message = "Category name is required and should not be empty.")
    @Size(min = 1, max = 100, message = "Category must be between 10 and 100 characters.")
    private String category;

    @NotNull(message = "Budget Limit is required and should not be null.")
    private Double budgetLimit;

    @NotNull(message = "Current Budget Amount is required and should not be null.")
    private Double currBudget;

    @NotNull(message = "monthYear must be in <Sept2024> format.")
    private String monthYear;

}
