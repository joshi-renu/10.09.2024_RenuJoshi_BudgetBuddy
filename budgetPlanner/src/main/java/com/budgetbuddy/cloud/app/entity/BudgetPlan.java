package com.budgetbuddy.cloud.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="budget-id")
    private int budgetId;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Double budgetLimit;

    @Column(nullable = false)
    private Double currBudget;

    @Column(nullable = false)
    private String monthYear;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private Instant createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private Instant modifiedAt;

    @ManyToOne
    @JoinColumn(name="user-id", nullable=false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy="budgetPlan")
    @JsonManagedReference
    private List<Expense> expense;

}
