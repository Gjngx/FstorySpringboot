package com.example.fstory.repositories;

import com.example.fstory.model.Truyen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TruyenRepositories extends JpaRepository<Truyen,Long> {
    List <Truyen> findBytentruyen(String tentruyen);
}
