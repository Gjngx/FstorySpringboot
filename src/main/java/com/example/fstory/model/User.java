package com.example.fstory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Objects;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Pattern(regexp = "^[a-z0-9_-]{3,16}$", message = "Tối thiểu ba ký tự, tối đa 16 kí tự!")
    @Column(nullable = false, unique = true, length = 35)
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Tối thiểu tám ký tự, ít nhất một chữ cái viết hoa, một chữ cái viết thường và một số!")
    @Column(nullable = false, length = 255)
    private String password;

    @Pattern(regexp = "^[a-z0-9_-]{3,16}$", message = "Tối thiểu ba ký tự, tối đa 16 kí tự!")
    @Column(nullable = false, length = 60)
    private String hoten;

    @Column(nullable = false)
    private Date ngaysinh;

    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$", message = "Tên mail không hợp lệ!")
    @Column(nullable = false, unique = true, length = 255)
    private String mail;

    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})", message = "Số điện thoại không hợp lệ!")
    @Column(nullable = false, unique = true, length = 15)
    private String sdt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp
    private Date ngaytao;

    public User(){}

    public User(String username, String password, String hoten, Date ngaysinh, String mail, String sdt, Date ngaytao) {
        this.username = username;
        this.password = password;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.mail = mail;
        this.sdt = sdt;
        this.ngaytao = ngaytao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hoten='" + hoten + '\'' +
                ", ngaysinh=" + ngaysinh +
                ", mail='" + mail + '\'' +
                ", sdt='" + sdt + '\'' +
                ", ngaytao='" + ngaytao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(hoten, user.hoten) && Objects.equals(ngaysinh, user.ngaysinh) && Objects.equals(mail, user.mail) && Objects.equals(sdt, user.sdt) && Objects.equals(ngaytao, user.ngaytao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, hoten, ngaysinh, mail, sdt, ngaytao);
    }
}
