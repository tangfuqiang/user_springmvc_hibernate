package com.qingshixun.project.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false,updatable = false)
    private String account;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String occupation;

    @Column(nullable = false)
    private String hobby;

    @Column(nullable = false)
    private char sex;


    @JSONField(format = "YYYY-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @JSONField(format = "YYYY-MM-dd HH:mm:ss")
    private Date createtime;

    public UserModel() {
    }

    private String imageurl;

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", occupation='" + occupation + '\'' +
                ", hobby='" + hobby + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", createtime=" + createtime +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}
