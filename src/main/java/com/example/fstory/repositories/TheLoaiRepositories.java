package com.example.fstory.repositories;

import com.example.fstory.model.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheLoaiRepositories extends JpaRepository<TheLoai,Long> {
    List<TheLoai> findBytheloai(String theloai);
}
