package com.budgetbuddy.cloud.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="alert-id")
    private int alertId;

    @Column(nullable = false)
    private String alertType;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private int sourceId;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime modifiedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
        modifiedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedAt = OffsetDateTime.now();
    }
}
