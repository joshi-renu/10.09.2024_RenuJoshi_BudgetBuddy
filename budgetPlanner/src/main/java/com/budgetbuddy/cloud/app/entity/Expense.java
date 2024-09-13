package com.budgetbuddy.cloud.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="expense-id")
    private int expenseId;

    @Column(nullable = false)
    @NotNull(message = "Description is required and should not be null.")
    @NotEmpty(message = "Description is required and should not be empty.")
    @Size(min = 5, max = 300, message = "Description must be between 5 and 300 characters.")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Amount is required and should not be null.")
    private Double amount;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", message = "Date must be in dd/mm/yyyy format")
    private Date date;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private Instant createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private Instant modifiedAt;

    @ManyToOne
    @JoinColumn(name="user-id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="budget-id", nullable=false)
    private BudgetPlan budgetPlan;

}
