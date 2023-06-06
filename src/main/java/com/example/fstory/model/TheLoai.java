package com.example.fstory.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "theloai")
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true, length = 255)
    private String theloai;
    @Column(nullable = false, unique = true, length = 255)
    private String tieude;

    @OneToMany(mappedBy = "theloai")
    private List<Truyen> truyenList;

    public TheLoai (){}

    public TheLoai(String theloai, String tieude) {
        this.theloai = theloai;
        this.tieude = tieude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }


    @Override
    public String toString() {
        return "TheLoai{" +
                "id=" + id +
                ", theloai='" + theloai + '\'' +
                ", tieude='" + tieude +
                '}';
    }
}
