package com.example.fstory.model;

import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "tacgia")
public class TacGia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true, length = 255)
    private String tacgia;
    @Column(nullable = false, unique = true, length = 255)
    private String tieude;

    @OneToMany(mappedBy = "tacgia")
    private List<Truyen> truyenList;


    public TacGia (){}

    public TacGia(String tacgia, String tieude) {
        this.tacgia = tacgia;
        this.tieude = tieude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }


    @Override
    public String toString() {
        return "TacGia{" +
                "id=" + id +
                ", tacgia='" + tacgia + '\'' +
                ", tieude='" + tieude +
                '}';
    }

}
