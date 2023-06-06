package com.example.fstory.repositories;

import com.example.fstory.model.Truyen;
import com.example.fstory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositories extends JpaRepository<User,Long> {
    List<User> findByusername(String username);
}
