package com.budgetbuddy.cloud.app.controller;

import com.budgetbuddy.cloud.app.dto.FinancialGoalRequest;
import com.budgetbuddy.cloud.app.dto.FinancialGoalResponse;
import com.budgetbuddy.cloud.app.dto.UserRequest;
import com.budgetbuddy.cloud.app.dto.UserResponse;
import com.budgetbuddy.cloud.app.entity.BudgetPlan;
import com.budgetbuddy.cloud.app.feignclients.BudgetPlanFeignClient;
import com.budgetbuddy.cloud.app.service.FinancialGoalService;
import com.budgetbuddy.cloud.app.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FinancialGoalService financialGoalService;

    @Autowired
    private BudgetPlanFeignClient budgetPlanFeignClient;

    @GetMapping("/dummy")
    public List<BudgetPlan> dummyBP(){
        List<BudgetPlan> budgetPlansByUserId = budgetPlanFeignClient.getBudgetPlansByUserId(1);
        return budgetPlansByUserId;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest user){
        return userService.createUser(user);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable @Positive int id){
       return  userService.getUserById(id);
    }

    @GetMapping("/getAll")
    public List<UserRequest> getAllUsers(){
        return  userService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable @Positive int id, @Valid @RequestBody UserRequest userReq){
        return userService.updateUser(id, userReq);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        return userService.deleteUserById(id);
    }

//    FINANCIAL GOALS OF USER

//    create financialGoal by userId
    @PostMapping("{id}/createFinancialGoal")
    public ResponseEntity<FinancialGoalResponse> createFinancialGoalIdByUserId(@PathVariable @Positive int id, @Valid @RequestBody FinancialGoalRequest req,   BindingResult bindingResult){
        return financialGoalService.createFinancialGoalIdByUserId(id, req);
    }

//    update financialGoal of a userId by financialGoalId
    @PutMapping("{id}/updateFinancialGoal/{goalId}")
    public ResponseEntity<FinancialGoalResponse> updateFinancialGoalIdByUserId(@PathVariable @Positive int id, @PathVariable("goalId") int goalId, @Valid @RequestBody FinancialGoalRequest req){
        return financialGoalService.updateFinancialGoalIdByUserId(id, goalId, req);
    }

//    delete a financialGoalId of a User Id
    @DeleteMapping("{id}/delete/{goalId}")
    public String deleteFinancialGoalIdByUserId(@PathVariable("id") int id, @PathVariable("goalId") int goalId) {
        return financialGoalService.deleteFinancialGoalIdByUserId(id, goalId);
    }

}
