package com.shadowxz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class User {

    private int id;

    private String userName;

    private String password;

    private String email;

    private int active;

    private String validateCode;

    private int sex;

    private String selfIntroduce;

    private int state;

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

    private int massageNumber;

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

    public User(String userName, String password, String email, int active, String validateCode, int sex, String selfIntroduce, int state, Date registerTime, int massageNumber) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSelfIntroduce() {
        return selfIntroduce;
    }

    public void setSelfIntroduce(String selfIntroduce) {
        this.selfIntroduce = selfIntroduce == null ? null : selfIntroduce.trim();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public int getMassageNumber() {
        return massageNumber;
    }

    public void setMassageNumber(int massageNumber) {
        this.massageNumber = massageNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
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

    public void setForbidTime(int days) {
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