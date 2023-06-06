package com.example.fstory.repositories;


import com.example.fstory.model.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrangThaiRepositories extends JpaRepository<TrangThai,Long> {
    List<TrangThai> findBytrangthai(String trangthai);
}
