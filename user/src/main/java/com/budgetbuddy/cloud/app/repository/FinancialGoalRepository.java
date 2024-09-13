package com.budgetbuddy.cloud.app.repository;

import com.budgetbuddy.cloud.app.entity.FinancialGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, Integer> {
    Optional<FinancialGoal> findByUser_UserIdAndGoalId(int userId, int goalId);
    List<FinancialGoal> findByUser_UserId(int userId);
}
