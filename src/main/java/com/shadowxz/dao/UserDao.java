package com.shadowxz.dao;

import com.shadowxz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    int deleteByPrimaryKey(int id);

    int insert(User record);

    String selectPasswordByUserName(String userName);

    int insertSelective(User record);

    User selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String userName);

    User selectByForLoginByUserName(String userName);

    User selectByForLoginByEmail(String email);

    User selectByEmail(String email);

    List<User> selectAllUsers(int offset);

    User selectDetailUserById(int id);
}