package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserAccount;

import jakarta.transaction.Transactional;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount, Integer> {

    @Modifying
    @Transactional
    @Query("update UserAccount set activeSw = :status where userId = :userId")
    public void updateUserAccStatus(@Param("userId") Integer userId, @Param("status") String status);
}

