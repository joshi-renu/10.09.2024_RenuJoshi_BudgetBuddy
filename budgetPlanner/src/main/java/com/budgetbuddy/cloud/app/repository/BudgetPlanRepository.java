package com.budgetbuddy.cloud.app.repository;

import com.budgetbuddy.cloud.app.entity.BudgetPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetPlanRepository extends JpaRepository<BudgetPlan, Integer> {
    List<BudgetPlan> findByUser_UserId(int userId);
}
