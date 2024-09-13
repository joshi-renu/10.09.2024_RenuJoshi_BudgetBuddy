package com.budgetbuddy.cloud.app.feignclients;

import com.budgetbuddy.cloud.app.dto.UserResponse;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(value = "USER-SERVICE", path = "/api/user")
public interface UserFeignClient {
    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable @Positive int id);

}
