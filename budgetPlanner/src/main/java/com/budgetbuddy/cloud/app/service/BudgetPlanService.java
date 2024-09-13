package com.budgetbuddy.cloud.app.service;

import com.budgetbuddy.cloud.app.dto.*;
import com.budgetbuddy.cloud.app.entity.BudgetPlan;
import com.budgetbuddy.cloud.app.entity.Expense;
import com.budgetbuddy.cloud.app.entity.FinancialGoal;
import com.budgetbuddy.cloud.app.entity.User;
import com.budgetbuddy.cloud.app.exceptionHandler.BudgetNotFoundException;
import com.budgetbuddy.cloud.app.feignclients.UserFeignClient;
import com.budgetbuddy.cloud.app.repository.BudgetPlanRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BudgetPlanService {

    @Autowired
    BudgetPlanRepository budgetPlanRepository;

    @Autowired
    UserFeignClient userFeignClient;

    public ResponseEntity<BudgetResponse> createBudgetPlanForUser(@Positive int userid, @Valid BudgetRequest budgetRequest) {
        ResponseEntity<UserResponse> userResEntity = userFeignClient.getUserById(userid);
        if (userResEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to fetch user details");
        }
        UserResponse userResponse = userResEntity.getBody();
        User user = convertToUserEntity(userResponse);

        BudgetPlan budgetPlan = new BudgetPlan();
        budgetPlan.setCategory(budgetRequest.getCategory());
        budgetPlan.setBudgetLimit(budgetRequest.getBudgetLimit());
        budgetPlan.setCurrBudget(budgetRequest.getCurrBudget());
        budgetPlan.setMonthYear(budgetRequest.getMonthYear());
        budgetPlan.setUser(user);

        budgetPlanRepository.save(budgetPlan);
            return new ResponseEntity<>(mapToBudgetResponse(budgetPlan,user), HttpStatus.CREATED);
    }

    public List<BudgetPlan> getBudgetPlansByUserId(@Positive int userId) {
        System.out.println("entered budget ms");
        List<BudgetPlan> budgetPlan= budgetPlanRepository.findByUser_UserId(userId);
        System.out.println(budgetPlan);
//        if(!budgetPlan.isEmpty()){
            log.info("list of budgetplans fetched successfully");
            return budgetPlan;
//        }
//        else{
//            throw new BudgetNotFoundException("No budget exists with the user "+userId);
//        }
    }

//    public ResponseEntity<BudgetResponse> updateBudgetPlanIdByUserId(@Positive int userid, int budgetId, @Valid BudgetRequest req) {
//    }
//
//    public String deleteBudgetPlanIdByUserId(int userid, int budgetId) {
//    }


    public BudgetResponse mapToBudgetResponse(BudgetPlan budget, User user){
        BudgetResponse budgetResponse = new BudgetResponse();
        budgetResponse.setBudgetLimit(budget.getBudgetLimit());
        budgetResponse.setCurrBudget(budget.getCurrBudget());
        budgetResponse.setCategory(budget.getCategory());
        budgetResponse.setUser(user);
        budgetResponse.setMonthYear(budget.getMonthYear());
//        budgetResponse.setExpenses(expenses.stream().map(this::mapToExpenseSummaryDTO).collect(Collectors.toList()));
        return budgetResponse;
    }

    private ExpenseSummaryDTO mapToExpenseSummaryDTO(Expense expense) {
        ExpenseSummaryDTO expenseSummaryDTO = new ExpenseSummaryDTO();
        expenseSummaryDTO.setExpenseId(expense.getExpenseId());
        expenseSummaryDTO.setDate(expense.getDate());
        expenseSummaryDTO.setAmount(expense.getAmount());
        expenseSummaryDTO.setDescription(expense.getDescription());
        return expenseSummaryDTO;
    }

    public User convertToUserEntity(UserResponse userResponse){
        User user = new User();
        user.setUserName(userResponse.getUserName());
        user.setUserId(userResponse.getUserId());
        user.setEmail(userResponse.getEmail());
        return user;
    }



}

