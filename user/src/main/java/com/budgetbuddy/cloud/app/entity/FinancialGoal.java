package com.budgetbuddy.cloud.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goalId;

    @Column(nullable = false)
    private Double targetAmount;

    @Column(nullable = false)
    private Double currAmount;

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

}
