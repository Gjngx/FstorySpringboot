package com.example.fstory.repositories;

import com.example.fstory.model.ChuongTruyen;
import com.example.fstory.model.Truyen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChuongTruyenRepositories extends JpaRepository<ChuongTruyen,Long> {
    List<ChuongTruyen> findBysochuong(Long sochuong);
    List<ChuongTruyen> findBytruyen(Truyen truyen);
}
