package com.example.fstory.controller;

import com.example.fstory.model.ResponseObject;
import com.example.fstory.model.TacGia;
import com.example.fstory.repositories.TacGiaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/v1/tacgia")
public class TacGiaController {
    @Autowired
    private TacGiaRepositories repository;

    @GetMapping("")
    List<TacGia> getAllTacGia() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<TacGia> foundTacgia = repository.findById(id);
        if(foundTacgia.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xuất tác giả thành công", foundTacgia)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "không tìm thấy tác giả với id = " + id, "")
            );
        }
    }
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertTacGia(@RequestBody TacGia newTacgia){
        List <TacGia> foundTacgia = repository.findBytacgia(newTacgia.getTacgia().trim());
        if(foundTacgia.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Tên tác giả đã tồn tại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Thêm tác giả thành công", repository.save(newTacgia))
        );
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateTacGia(@RequestBody TacGia newTacgia, @PathVariable Long id){
        TacGia updateTacgia = repository.findById(id)
                .map(tacgia -> {
                    tacgia.setTacgia(newTacgia.getTacgia());
                    tacgia.setTieude(newTacgia.getTieude());
                    return repository.save(tacgia);
                }).orElseGet(()->{
                    newTacgia.setId(id);
                    return repository.save(newTacgia);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Cập nhật tác giả thành công", updateTacgia)
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteTacGia(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Xoá tác giả thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Xoá tác giả thất bại", "")
        );
    }
}

