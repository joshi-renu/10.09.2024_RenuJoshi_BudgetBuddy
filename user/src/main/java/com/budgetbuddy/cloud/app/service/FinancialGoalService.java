package com.budgetbuddy.cloud.app.service;

import com.budgetbuddy.cloud.app.dto.FinancialGoalRequest;
import com.budgetbuddy.cloud.app.dto.FinancialGoalResponse;
import com.budgetbuddy.cloud.app.entity.FinancialGoal;
import com.budgetbuddy.cloud.app.entity.User;
import com.budgetbuddy.cloud.app.exceptionHandler.UserNotFoundException;
import com.budgetbuddy.cloud.app.repository.FinancialGoalRepository;
import com.budgetbuddy.cloud.app.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@Slf4j
public class FinancialGoalService {

    @Autowired
    FinancialGoalRepository financialGoalRepository;

    @Autowired
    UserRepository userRepository;

    public String deleteFinancialGoalIdByUserId(int userId, int goalId) {
        Optional<FinancialGoal> goalOptional = financialGoalRepository.findByUser_UserIdAndGoalId(userId, goalId);
        String username = userRepository.findUserNameByUserId(userId);
        if (goalOptional.isPresent()) {
            String monthYear = goalOptional.get().getMonthYear();
            financialGoalRepository.delete(goalOptional.get());
            log.info("Financial Goal of {} for user {} Deleted Successfully!", monthYear,username);
            return "Financial Goal of "+monthYear+" for user "+username+" Deleted Successfully!";
        } else {
            throw new UserNotFoundException("User "+userId+" does not exist with this "+goalId);
        }
    }

    public ResponseEntity<FinancialGoalResponse> updateFinancialGoalIdByUserId(int userId, int goalId, @Valid FinancialGoalRequest req) {
        log.info("findUserNameByUserId {}",userRepository.findUserNameByUserId(userId));
        String username = userRepository.findUserNameByUserId(userId);
        log.info(username);
        Optional<FinancialGoal> goalOptional = financialGoalRepository.findByUser_UserIdAndGoalId(userId, goalId);
        if(goalOptional.isPresent()){
            FinancialGoal fg = goalOptional.get();
            fg.setTargetAmount(req.getTargetAmount());
            fg.setMonthYear(req.getMonthYear());
            fg.setCurrAmount(req.getCurrAmount());
            log.info("Financial Goal of {} for user {} Updated Successfully!", req.getMonthYear(),username);
            return new ResponseEntity<>(convertFinancialGoalToResponse(financialGoalRepository.save(fg)), HttpStatus.OK);

        } else {
            throw new UserNotFoundException("User "+userId+" does not exist with this "+goalId);
        }

    }

    public ResponseEntity<FinancialGoalResponse> createFinancialGoalIdByUserId(int id, @Valid @RequestBody FinancialGoalRequest req) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            FinancialGoal financialGoal = financialGoalRepository.save(convertGoalReqToGoal(user, req));
            log.info("Financial Goal of {} for user {} Created Successfully!", req.getMonthYear(),user.getUserName());
            return new ResponseEntity<>(convertFinancialGoalToResponse(financialGoal), HttpStatus.CREATED);
        }
        else{
            throw new UserNotFoundException("User "+id+" does not exist.");
        }
    }

    public FinancialGoalResponse convertFinancialGoalToResponse(FinancialGoal goal){
        FinancialGoalResponse fgResponse = new FinancialGoalResponse();
        fgResponse.setTargetAmount(goal.getTargetAmount());
        fgResponse.setMonthYear(goal.getMonthYear());
        fgResponse.setCurrAmount(goal.getCurrAmount());
        fgResponse.setUser(goal.getUser());
        return fgResponse;
    }

    public FinancialGoal convertGoalReqToGoal(User user, FinancialGoalRequest fgReq) {
        FinancialGoal fg = new FinancialGoal();
        fg.setTargetAmount(fgReq.getTargetAmount());
        fg.setCurrAmount(fgReq.getCurrAmount());
        fg.setMonthYear(fgReq.getMonthYear());
        fg.setUser(user);
        return fg;
    }

}
