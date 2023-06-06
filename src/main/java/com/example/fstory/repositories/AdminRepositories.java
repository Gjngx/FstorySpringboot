package com.example.fstory.repositories;

import com.example.fstory.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepositories extends JpaRepository<Admin,Long> {
    List<Admin> findByusername(String username);
}
