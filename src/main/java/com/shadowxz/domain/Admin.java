package com.shadowxz.domain;

/**
 * Created by xz on 2017/5/4.
 */
public class Admin {

    private Integer id;

    private String userName;

    private String password;

    private Integer state;

    public Admin(String userName,String password) {
        this.userName = userName;
        this.password = password;
    }

    public Admin(Integer id, String userName, String password, Integer state) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.state = state;
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
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String toString(){
        String admin = "admin:" + "[" +
                "id=" + id +
                "userName=" + userName +
                "password=" + password +
                "state=" + state +
                "]";
        return admin;
    }
}
