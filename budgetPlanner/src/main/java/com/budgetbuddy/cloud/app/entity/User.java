package com.budgetbuddy.cloud.app.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user-id")
    private int userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private Instant createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private Instant modifiedAt;

    @OneToMany(mappedBy="user")
    @JsonManagedReference
    private List<Expense> expenses;

    @OneToMany(mappedBy="user")
    @JsonManagedReference
    private List<BudgetPlan> budgetPlans;

    @OneToMany(mappedBy="user")
    @JsonManagedReference
    private List<FinancialGoal> financialGoals;
}
