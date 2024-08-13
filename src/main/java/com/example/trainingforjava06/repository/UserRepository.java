package com.example.trainingforjava06.repository;

import com.example.trainingforjava06.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findBySdt(String sdt);
}
