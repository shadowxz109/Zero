package com.shadowxz.service.Impl;

import com.shadowxz.dao.AdminDao;
import com.shadowxz.domain.Admin;
import com.shadowxz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xz on 2017/5/4.
 */
@Service
public class AdminServiceImpl implements AdminService{


    @Autowired
    AdminDao adminDao;

    public void addAdmin(Admin admin){
        adminDao.insert(admin);
    }

    public void deleteAdmin(int adminId){
        adminDao.deleteByPrimaryKey(adminId);
    }

    public Admin findById(int id){
        return adminDao.selectByPrimaryKey(id);
    }

    public Admin findByUserName(String userName) {
        return adminDao.selectByUserName(userName);
    }

    public List<Admin> findAllAdmins(int page){
        List<Admin> admins = adminDao.selectAllUsers((page-1)*10);
        for (Admin admin:admins) {
            if(admin.getState() == 0)
                admins.remove(admin);
        }
        return admins;
    }
}
