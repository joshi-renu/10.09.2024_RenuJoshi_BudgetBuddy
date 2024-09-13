package com.budgetbuddy.cloud.app.repository;

import com.budgetbuddy.cloud.app.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
