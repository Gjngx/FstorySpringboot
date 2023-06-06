package com.example.fstory.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "chuongtruyen")
public class ChuongTruyen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_truyen")
    private Truyen truyen;
    @Column(nullable = false)
    private Long sochuong;

    @Column(nullable = false, length = 255)
    private String tenchuong;

    @Column(nullable = false, length = 1000000)
    private String noidung;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp
    private Date ngaydang;

    public ChuongTruyen(){}

    public ChuongTruyen(Truyen truyen, Long sochuong, String tenchuong, String noidung, Date ngaydang) {
        this.truyen = truyen;
        this.sochuong = sochuong;
        this.tenchuong = tenchuong;
        this.noidung = noidung;
        this.ngaydang = ngaydang;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Truyen getTruyen() {
        return truyen;
    }

    public void setTruyen(Truyen truyen) {
        this.truyen = truyen;
    }

    public Long getSochuong() {
        return sochuong;
    }

    public void setSochuong(Long sochuong) {
        this.sochuong = sochuong;
    }

    public String getTenchuong() {
        return tenchuong;
    }

    public void setTenchuong(String tenchuong) {
        this.tenchuong = tenchuong;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Date getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(Date ngaydang) {
        this.ngaydang = ngaydang;
    }

    @Override
    public String toString() {
        return "ChuongTruyen{" +
                "id=" + id +
                ", truyen=" + truyen +
                ", sochuong=" + sochuong +
                ", tenchuong='" + tenchuong + '\'' +
                ", noidung='" + noidung + '\'' +
                ", ngaydang=" + ngaydang +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChuongTruyen that = (ChuongTruyen) o;
        return Objects.equals(id, that.id) && Objects.equals(truyen, that.truyen) && Objects.equals(sochuong, that.sochuong) && Objects.equals(tenchuong, that.tenchuong) && Objects.equals(noidung, that.noidung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, truyen, sochuong, tenchuong, noidung);
    }
}
