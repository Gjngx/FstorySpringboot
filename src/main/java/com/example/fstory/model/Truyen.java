package com.example.fstory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "truyen")
public class Truyen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="id_tacgia")
    private TacGia tacgia;

    @ManyToOne
    @JoinColumn(name="id_theloai")
    private TheLoai theloai;

    @ManyToOne
    @JoinColumn(name = "id_trangthai")
    private TrangThai trangthai;

    @Column(nullable = false, unique = true, length = 255)
    private String tentruyen;

    @Column(nullable = false, length = 255)
    private String anhtruyen;

    @Column(nullable = false, unique = true, length = 255)
    private String tieude;

    @Column(nullable = false, length = 500)
    private String gioithieu;

    @Column(nullable = false)
    private long luotxem;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp
    private Date ngaydang;

    @OneToMany(mappedBy = "truyen")
    private List<ChuongTruyen> chuongTruyenList;

    public Truyen (){}

    public Truyen(TacGia tacgia, TheLoai theloai, TrangThai trangthai, String tentruyen, String anhtruyen, String tieude, String gioithieu, long luotxem, Date ngaydang) {
        this.tacgia = tacgia;
        this.theloai = theloai;
        this.trangthai = trangthai;
        this.tentruyen = tentruyen;
        this.anhtruyen = anhtruyen;
        this.tieude = tieude;
        this.gioithieu = gioithieu;
        this.luotxem = luotxem;
        this.ngaydang = ngaydang;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TacGia getTacgia() {
        return tacgia;
    }

    public void setTacgia(TacGia tacgia) {
        this.tacgia = tacgia;
    }

    public TheLoai getTheloai() {
        return theloai;
    }
    public void setTheloai(TheLoai theloai) {
        this.theloai = theloai;
    }

    public TrangThai getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(TrangThai trangthai) {
        this.trangthai = trangthai;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public String getAnhtruyen() {
        return anhtruyen;
    }

    public void setAnhtruyen(String anhtruyen) {
        this.anhtruyen = anhtruyen;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getGioithieu() {
        return gioithieu;
    }

    public void setGioithieu(String gioithieu) {
        this.gioithieu = gioithieu;
    }

    public long getLuotxem() {
        return luotxem;
    }

    public void setLuotxem(long luotxem) {
        this.luotxem = luotxem;
    }

    public Date getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(Date ngaydang) {

        this.ngaydang = ngaydang;
    }

    @Override
    public String toString() {
        return "Truyen{" +
                "id=" + id +
                ", tacgia=" + tacgia +
                ", theloai=" + theloai +
                ", trangthai=" + trangthai +
                ", tentruyen='" + tentruyen + '\'' +
                ", anhtruyen='" + anhtruyen + '\'' +
                ", tieude='" + tieude + '\'' +
                ", gioithieu='" + gioithieu + '\'' +
                ", luotxem=" + luotxem +
                ", ngaydang=" + ngaydang +
                '}';
    }
}
