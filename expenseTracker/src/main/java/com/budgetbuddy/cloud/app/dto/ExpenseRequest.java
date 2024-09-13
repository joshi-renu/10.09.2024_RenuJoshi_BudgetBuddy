package com.budgetbuddy.cloud.app.dto;

import com.budgetbuddy.cloud.app.entity.BudgetPlan;
import com.budgetbuddy.cloud.app.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.Date;

@Data
public class ExpenseRequest {

    @NotNull(message = "Description is required and should not be null.")
    @NotEmpty(message = "Description is required and should not be empty.")
    @Size(min = 5, max = 300, message = "Description must be between 5 and 300 characters.")
    private String description;

    @NotNull(message = "Amount is required and should not be null.")
    private Double amount;

    @NotNull(message = "Date is required and should not be null.")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", message = "Date must be in dd/mm/yyyy format")
    private Date date;

    @NotNull(message = "UserId is required and should not be null.")
    @Positive(message="User Id should be greater than 0.")
    private User userId;

    @NotNull(message = "BudgetPlan Id is required and should not be null.")
    @Positive(message="BudgetPlan Id should be greater than 0.")
    private BudgetPlan budgetId;
}
