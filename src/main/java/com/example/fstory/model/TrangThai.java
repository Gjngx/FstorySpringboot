package com.example.fstory.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "trangthai")
public class TrangThai {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true, length = 255)
    private String trangthai;

    @OneToMany(mappedBy = "trangthai")
    private List<Truyen> truyenList;

    public TrangThai (){}

    public TrangThai(String trangthai) {
        this.trangthai = trangthai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "TrangThai{" +
                "id=" + id +
                ", trangthai='" + trangthai + '\'' +
                '}';
    }
}
