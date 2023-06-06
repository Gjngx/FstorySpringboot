package com.example.fstory.repositories;

import com.example.fstory.model.TacGia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TacGiaRepositories extends JpaRepository<TacGia,Long> {
    List<TacGia> findBytacgia(String tacgia);
}
