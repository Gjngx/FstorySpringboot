package com.example.fstory.controller;

import com.example.fstory.model.ResponseObject;
import com.example.fstory.model.TacGia;
import com.example.fstory.model.TheLoai;
import com.example.fstory.repositories.TheLoaiRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/v1/theloai")
public class TheLoaiController {
    @Autowired
    private TheLoaiRepositories repository;

    @GetMapping("")
    List<TheLoai> getAllTheLoai() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<TheLoai> foundTheLoai = repository.findById(id);
        if(foundTheLoai.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xuất thể loại thành công", foundTheLoai)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "không tìm thấy thể loại với id = " + id, "")
            );
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertTheLoai(@RequestBody TheLoai newTheLoai){
        List <TheLoai> foundTheLoai = repository.findBytheloai(newTheLoai.getTheloai().trim());
        if(foundTheLoai.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Tên thể loại đã tồn tại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Thêm thể loại thành công", repository.save(newTheLoai))
        );
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateTheLoai(@RequestBody TheLoai newTheLoai, @PathVariable Long id){
        TheLoai updateTheLoai = repository.findById(id)
                .map(theloai -> {
                    theloai.setTheloai(newTheLoai.getTheloai());
                    theloai.setTieude(newTheLoai.getTieude());
                    return repository.save(theloai);
                }).orElseGet(()->{
                    newTheLoai.setId(id);
                    return repository.save(newTheLoai);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Cập nhật thể loại thành công", updateTheLoai)
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteTheLoai(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Xoá thể loại thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Xoá thể loại thất bại", "")
        );
    }
}
