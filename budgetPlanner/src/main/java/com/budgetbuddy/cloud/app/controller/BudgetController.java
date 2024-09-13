package com.budgetbuddy.cloud.app.controller;

import com.budgetbuddy.cloud.app.dto.BudgetRequest;
import com.budgetbuddy.cloud.app.dto.BudgetResponse;
import com.budgetbuddy.cloud.app.entity.BudgetPlan;
import com.budgetbuddy.cloud.app.service.BudgetPlanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgetPlan")
public class BudgetController {

    @Autowired
    BudgetPlanService budgetPlanService;

    @PostMapping("/{userid}/create")
    public ResponseEntity<BudgetResponse> createBudgetPlanForUser(@PathVariable @Positive int userid, @Valid @RequestBody BudgetRequest bReq){
        return budgetPlanService.createBudgetPlanForUser(userid,bReq);
    }

    @GetMapping("/{userId}/getBudgetPlan")
    public List<BudgetPlan> getBudgetPlansByUserId(@PathVariable @Positive int userId){
        return budgetPlanService.getBudgetPlansByUserId(userId);
    }

//    @PutMapping("/{userid}/updateBudgetPlan/{budgetId}")
//    public ResponseEntity<BudgetResponse> updateBudgetPlanIdByUserId(@PathVariable @Positive int userid, @PathVariable int budgetId, @Valid @RequestBody BudgetRequest req){
//        return budgetPlanService.updateBudgetPlanIdByUserId(userid, budgetId, req);
//    }
//
//    @DeleteMapping("/{userid}/delete/{budgetId}")
//    public String deleteBudgetPlanIdByUserId(@PathVariable int userid, @PathVariable int budgetId) {
//        return budgetPlanService.deleteBudgetPlanIdByUserId(userid, budgetId);
//    }
}
