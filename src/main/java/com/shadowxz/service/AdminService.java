package com.shadowxz.service;

import com.shadowxz.domain.Admin;

import java.util.List;

/**
 * Created by xz on 2017/5/4.
 */
public interface AdminService {

    void deleteAdmin(int adminId);

    void addAdmin(Admin admin);

    Admin findById(int id);

    Admin findByUserName(String userName);

    //为了保护超级管理员的隐密性，不会返回超级管理员
    List<Admin> findAllAdmins(int page);

}
