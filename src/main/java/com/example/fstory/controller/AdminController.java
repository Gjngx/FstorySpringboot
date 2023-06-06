package com.example.fstory.controller;


import com.example.fstory.model.*;
import com.example.fstory.repositories.AdminRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/v1/admin")
public class AdminController {
    @Autowired
    private AdminRepositories repository;

    @GetMapping("")
    List<Admin> getAllAdmin() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<Admin> foundUser = repository.findById(id);
        if(foundUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xuất admin thành công", foundUser)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "không tìm thấy admin với id = " + id, "")
            );
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertAdmin(@RequestBody Admin newAdmin){
        List <Admin> foundAdmin = repository.findByusername(newAdmin.getUsername().trim());
        if(foundAdmin.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Tên admin đã tồn tại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Thêm admin thành công", repository.save(newAdmin))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateAdmin(@RequestBody Admin newAdmin, @PathVariable Long id){
        Admin updateAdmin = repository.findById(id)
                .map(admin -> {
                    admin.setHoten(newAdmin.getHoten());
                    admin.setMail(newAdmin.getMail());
                    admin.setPassword(newAdmin.getPassword());
                    return repository.save(admin);
                }).orElseGet(()->{
                    newAdmin.setId(id);
                    return repository.save(newAdmin);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Cập nhật admin thành công", updateAdmin)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteAdmin(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Xoá admin thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Xoá admin thất bại", "")
        );
    }
}
