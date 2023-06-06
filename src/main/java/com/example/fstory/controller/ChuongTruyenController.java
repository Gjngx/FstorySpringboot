package com.example.fstory.controller;

import com.example.fstory.model.ChuongTruyen;
import com.example.fstory.model.ResponseObject;
import com.example.fstory.model.Truyen;
import com.example.fstory.repositories.ChuongTruyenRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/v1/chuongtruyen")
public class ChuongTruyenController {
    @Autowired
    private ChuongTruyenRepositories repository;

    @GetMapping("/truyen")
    public ResponseEntity<List<ChuongTruyen>> getChuongByTruyen(@RequestParam Truyen truyen) {
        return new ResponseEntity<List<ChuongTruyen>>(repository.findBytruyen(truyen), HttpStatus.OK);
    }


    @GetMapping("")
    List<ChuongTruyen> getAllchuongtruyen() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<ChuongTruyen> foundChuongtruyen = repository.findById(id);
        if(foundChuongtruyen.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xuất chương truyện thành công", foundChuongtruyen)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "không tìm thấy chương truyện với id = " + id, "")
            );
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertChuongTruyen(@RequestBody ChuongTruyen newChuongTruyen){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Thêm chương truyện thành công", repository.save(newChuongTruyen))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateChuongTruyen(@RequestBody ChuongTruyen newChuongtruyen, @PathVariable Long id){
        ChuongTruyen updateChuongtruyen = repository.findById(id)
                .map(chuongTruyen -> {
                    chuongTruyen.setTruyen(newChuongtruyen.getTruyen());
                    chuongTruyen.setSochuong(newChuongtruyen.getSochuong());
                    chuongTruyen.setTenchuong(newChuongtruyen.getTenchuong());
                    chuongTruyen.setNoidung(newChuongtruyen.getNoidung());
                    return repository.save(chuongTruyen);
                }).orElseGet(()->{
                    newChuongtruyen.setId(id);
                    return repository.save(newChuongtruyen);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Cập nhật chương truyện thành công", updateChuongtruyen)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteChuongTruyen(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Xoá chương truyện thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Xoá chương truyện thất bại", "")
        );
    }

}
