package com.example.fstory.controller;

import com.example.fstory.model.ResponseObject;
import com.example.fstory.model.Truyen;
import com.example.fstory.repositories.TruyenRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/v1/truyen")
public class TruyenController {
    @Autowired
    private TruyenRepositories repository;

    @GetMapping("")
    List<Truyen> getAllTruyen() {
        return repository.findAll();
    }

    @GetMapping("/desc")
    public List<Truyen> SortTruyen (@RequestParam String field) {
        return repository.findAll(Sort.by(Direction.DESC, field));
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<Truyen> foundTruyen = repository.findById(id);
        if(foundTruyen.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xuất truyện thành công", foundTruyen)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "không tìm thấy truyện với id = " + id, "")
            );
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertTruyen(@RequestBody Truyen NewTruyen){
        List <Truyen> foundTruyen = repository.findBytentruyen(NewTruyen.getTentruyen().trim());
        if(foundTruyen.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Tên truyện đã tồn tại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Thêm truyện thành công", repository.save(NewTruyen))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateTruyen(@RequestBody Truyen newTruyen, @PathVariable Long id){
        Truyen updateTruyen = repository.findById(id)
                .map(truyen -> {
                    truyen.setTacgia(newTruyen.getTacgia());
                    truyen.setTheloai(newTruyen.getTheloai());
                    truyen.setTrangthai(newTruyen.getTrangthai());
                    truyen.setTentruyen(newTruyen.getTentruyen());
                    truyen.setAnhtruyen(newTruyen.getAnhtruyen());
                    truyen.setTieude(newTruyen.getTieude());
                    truyen.setGioithieu(newTruyen.getGioithieu());
                    return repository.save(truyen);
                }).orElseGet(()->{
                    newTruyen.setId(id);
                    return repository.save(newTruyen);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Cập nhật truyện thành công", updateTruyen)
        );
    }


    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteTruyen(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Xoá truyện thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Xoá truyện thất bại", "")
        );
    }

}
