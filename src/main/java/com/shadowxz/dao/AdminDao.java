package com.shadowxz.dao;

import com.shadowxz.domain.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xz on 2017/5/4.
 */

@Repository
public interface AdminDao {

    int deleteByPrimaryKey(int id);

    int insert(Admin record);

    String selectPasswordByUserName(String userName);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectByUserName(String userName);

    List<Admin> selectAllUsers(int offset);
}
