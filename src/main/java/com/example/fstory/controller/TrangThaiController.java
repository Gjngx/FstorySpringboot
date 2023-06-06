package com.example.fstory.controller;


import com.example.fstory.model.ResponseObject;

import com.example.fstory.model.TrangThai;
import com.example.fstory.repositories.TrangThaiRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/v1/trangthai")
public class TrangThaiController {
    @Autowired
    private TrangThaiRepositories repository;

    @GetMapping("")
    List<TrangThai> getAllTrangThai() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<TrangThai> foundTrangThai = repository.findById(id);
        if(foundTrangThai.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xuất trạng thái thành công", foundTrangThai)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "không tìm thấy trạng thái với id = " + id, "")
            );
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertTrangThai(@RequestBody TrangThai newTrangThai){
        List <TrangThai> foundTrangThai = repository.findBytrangthai(newTrangThai.getTrangthai().trim());
        if(foundTrangThai.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Tên trạng thái đã tồn tại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Thêm trạng thái thành công", repository.save(newTrangThai))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateTrangThai(@RequestBody TrangThai newTrangThai, @PathVariable Long id){
        TrangThai updateTrangThai = repository.findById(id)
                .map(trangThai -> {
                    trangThai.setTrangthai(newTrangThai.getTrangthai());
                    return repository.save(trangThai);
                }).orElseGet(()->{
                    newTrangThai.setId(id);
                    return repository.save(newTrangThai);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Cập nhật trạng thái thành công", updateTrangThai)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteTrangThai(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Xoá trạng thái thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Xoá trạng thái thất bại", "")
        );
    }
}
