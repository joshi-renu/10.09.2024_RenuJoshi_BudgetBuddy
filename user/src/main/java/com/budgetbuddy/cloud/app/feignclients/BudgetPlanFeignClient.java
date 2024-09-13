package com.budgetbuddy.cloud.app.feignclients;

import com.budgetbuddy.cloud.app.dto.UserResponse;
import com.budgetbuddy.cloud.app.entity.BudgetPlan;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(value = "BUDGETPLANNER-SERVICE", url="http://localhost:8081")
public interface BudgetPlanFeignClient {
    @GetMapping("/api/budgetPlan/{userId}/getBudgetPlan")
    public List<BudgetPlan> getBudgetPlansByUserId(@PathVariable @Positive int userId);

}
