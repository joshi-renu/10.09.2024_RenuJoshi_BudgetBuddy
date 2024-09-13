package com.budgetbuddy.cloud.app.repository;

import com.budgetbuddy.cloud.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u.userName FROM User u WHERE u.userId = :userId")
    String findUserNameByUserId(int userId);
}
