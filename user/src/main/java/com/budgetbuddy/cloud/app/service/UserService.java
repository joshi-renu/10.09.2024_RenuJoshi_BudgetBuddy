package com.budgetbuddy.cloud.app.service;

import com.budgetbuddy.cloud.app.dto.BudgetPlanSummaryDTO;
import com.budgetbuddy.cloud.app.dto.FinancialGoalSummaryDTO;
import com.budgetbuddy.cloud.app.dto.UserRequest;
import com.budgetbuddy.cloud.app.dto.UserResponse;
import com.budgetbuddy.cloud.app.entity.BudgetPlan;
import com.budgetbuddy.cloud.app.entity.Expense;
import com.budgetbuddy.cloud.app.entity.FinancialGoal;
import com.budgetbuddy.cloud.app.entity.User;
import com.budgetbuddy.cloud.app.exceptionHandler.UserNotFoundException;
import com.budgetbuddy.cloud.app.feignclients.BudgetPlanFeignClient;
import com.budgetbuddy.cloud.app.repository.FinancialGoalRepository;
import com.budgetbuddy.cloud.app.repository.UserRepository;
import com.netflix.discovery.converters.Auto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BudgetPlanFeignClient budgetPlanFeignClient;

    @Autowired
    FinancialGoalRepository financialGoalRepository;

//    THROWS GENERIC EXCEPTION
    public ResponseEntity<UserResponse> createUser(@Valid UserRequest userReq) {
        User user = userRepository.save(convertUserRequestToUser(userReq));
        log.info("User created successfully");
        return new ResponseEntity<>(convertToUserResponse(user), HttpStatus.CREATED);
    }

//    THROWS CUSTOM EXCEPTION
    public ResponseEntity<UserResponse> getUserById(@Positive int id){
         User user = userRepository.findById(id)
                 .orElseThrow(()-> new UserNotFoundException("User "+id+" not found!"));
         if(user!=null){
//             List<Expense> expenses = expenseRepository.findByUserId(id);
             List<BudgetPlan> budgetPlans = budgetPlanFeignClient.getBudgetPlansByUserId(id);
             List<FinancialGoal> financialGoals = financialGoalRepository.findByUser_UserId(id);
             log.info("User {} fetched!", id);
             return new ResponseEntity<>(convertToUserResponse(user,budgetPlans, financialGoals), HttpStatus.OK);
         }
         else{
          throw new UserNotFoundException("User "+id+" does not exist!");
         }

    }

    public List<UserRequest> getAllUsers() {
      return userRepository.findAll()
              .stream()
              .map(x->
              {
                  UserRequest ur = new UserRequest();
                  ur.setUserName(x.getUserName());
                  ur.setEmail(x.getEmail());
                  return ur;
              })
              .collect(Collectors.toList());
    }

    public ResponseEntity<UserResponse> updateUser(@Positive int id, @Valid UserRequest userReq) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User "+id+" not found!"));
        user.setUserName(userReq.getUserName());
        user.setEmail(userReq.getEmail());
        List<FinancialGoal> fglist= user.getFinancialGoals();
        List<BudgetPlan> bplist = user.getBudgetPlans();
        log.info("User {} updated successfully!", id);
        return new ResponseEntity<>(convertToUserResponse(userRepository.save(user),bplist, fglist),HttpStatus.OK);
    }

    public String deleteUserById(@Positive int id) {
        String username = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User "+id+" does not exist!"))
                .getUserName();
        userRepository.deleteById(id);
        log.info("User {} Deleted Successfully!", username);
        return "User "+username+" Deleted Successfully!";
    }

    public UserResponse convertToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }

    public UserResponse convertToUserResponse(User user, List<BudgetPlan> budgetPlans,List<FinancialGoal> financialGoals){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());
//        userResponse.setExpenses(user.getExpense());
        userResponse.setBudgetPlans(budgetPlans.stream().map(this::mapToBudgetSummaryDTO).collect(Collectors.toList()));
        userResponse.setFinancialGoals(financialGoals.stream().map(this::mapToGoalSummaryDTO).collect(Collectors.toList()));
        return userResponse;
    }

    public User convertUserRequestToUser(UserRequest userRequest){
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        return user;
    }

    private FinancialGoalSummaryDTO mapToGoalSummaryDTO(FinancialGoal goal) {
        FinancialGoalSummaryDTO financialGoalSummaryDTO = new FinancialGoalSummaryDTO();
        financialGoalSummaryDTO.setGoalId(goal.getGoalId());
        financialGoalSummaryDTO.setMonthYear(goal.getMonthYear());
        financialGoalSummaryDTO.setCurrAmount(goal.getCurrAmount());
        financialGoalSummaryDTO.setTargetAmount(goal.getTargetAmount());
        return financialGoalSummaryDTO;
    }

    private BudgetPlanSummaryDTO mapToBudgetSummaryDTO(BudgetPlan bp) {
        BudgetPlanSummaryDTO budgetSummaryDTO = new BudgetPlanSummaryDTO();
        budgetSummaryDTO.setBudgetLimit(bp.getBudgetLimit());
        budgetSummaryDTO.setCurrBudget(bp.getCurrBudget());
        budgetSummaryDTO.setCategory(bp.getCategory());
        budgetSummaryDTO.setMonthYear(bp.getMonthYear());
        return budgetSummaryDTO;
    }

}
