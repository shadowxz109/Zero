package com.shadowxz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class User {

    private Integer id;

    private String userName;

    private String password;

    private String email;

    private Integer active;

    private String validateCode;

    private Integer sex;

    private String selfIntroduce;

    private Integer state;

    private Date forbidTime;

    private List<Post> posts;

    private List<Reply> replies;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date registerTime;

    private Integer massageNumber;

    public User(Integer id, String userName, String email, Integer active, String validateCode, Integer sex, String selfIntroduce, Integer state, Date registerTime, Integer massageNumber) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.active = active;
        this.validateCode = validateCode;
        this.sex = sex;
        this.selfIntroduce = selfIntroduce;
        this.state = state;
        this.registerTime = registerTime;
        this.massageNumber = massageNumber;
    }

    public User(String userName, String password, String email, Integer active, String validateCode, Integer sex, String selfIntroduce, Integer state, Date registerTime, Integer massageNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.active = active;
        this.validateCode = validateCode;
        this.sex = sex;
        this.selfIntroduce = selfIntroduce;
        this.state = state;
        this.registerTime = registerTime;
        this.massageNumber = massageNumber;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSelfIntroduce() {
        return selfIntroduce;
    }

    public void setSelfIntroduce(String selfIntroduce) {
        this.selfIntroduce = selfIntroduce == null ? null : selfIntroduce.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getMassageNumber() {
        return massageNumber;
    }

    public void setMassageNumber(Integer massageNumber) {
        this.massageNumber = massageNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public Date getForbidTime() {
        return forbidTime;
    }

    public void setForbidTime(Integer days) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        cl.add(Calendar.DATE,days);
        forbidTime = cl.getTime();
    }

    public String toString() {
        String user = "User:" + "[" +
                "id=" + id + "," +
                "userName=" + userName + "," +
                "email=" + email + "," +
                "sex=" + sex + "," +
                "selfIntroduce=" + selfIntroduce + "," +
                "state=" + state + "," +
                "registerTime=" + registerTime + "," +
                "messageNumber=" + massageNumber + "]";
        return user;
    }
}