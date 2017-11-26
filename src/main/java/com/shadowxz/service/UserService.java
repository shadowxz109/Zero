package com.shadowxz.service;

import com.shadowxz.domain.Post;
import com.shadowxz.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by xz on 2017/4/29.
 */
public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    void deleteUserById(int id);

    User findUserById(int id);

    String findPasswordByUserName(String userName);

    User findUserByUserName(String UserName);

    User findUserByEmail(String email);

    List<User> finaAllUsers(int page);

    Map<String,Object> processActive(String email,String vCode);

    void sendEmail(User user);

    User findUserDetailById(int id);

    Map<String,List<Post>> findUnreadMessage(int id);

    void updateMessageNumberById(int id,int number);

}
