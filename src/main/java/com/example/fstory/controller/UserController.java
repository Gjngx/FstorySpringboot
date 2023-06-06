package com.example.fstory.controller;


import com.example.fstory.model.ResponseObject;
import com.example.fstory.model.User;
import com.example.fstory.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    @Autowired
    private UserRepositories repository;

    @GetMapping("")
    List<User> getAllUser() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<User> foundUser = repository.findById(id);
        if(foundUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xuất user thành công", foundUser)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "không tìm thấy user với id = " + id, "")
            );
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser){
        List <User> foundUser = repository.findByusername(newUser.getUsername().trim());
        if(foundUser.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Tên user name đã tồn tại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Thêm user thành công", repository.save(newUser))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser, @PathVariable Long id){
        User updateUser = repository.findById(id)
                .map(user -> {
                    user.setHoten(newUser.getHoten());
                    user.setNgaysinh(newUser.getNgaysinh());
                    user.setMail(newUser.getMail());
                    user.setSdt(newUser.getSdt());
                    user.setPassword(newUser.getPassword());
                    return repository.save(user);
                }).orElseGet(()->{
                    newUser.setId(id);
                    return repository.save(newUser);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Cập nhật user thành công", updateUser)
        );
    }


    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Xoá user thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Xoá user thất bại", "")
        );
    }

}
