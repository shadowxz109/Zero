package com.shadowxz.service.Impl;

import com.shadowxz.dao.UserDao;
import com.shadowxz.domain.Constant;
import com.shadowxz.domain.User;
import com.shadowxz.service.UserService;
import com.shadowxz.util.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by xz on 2017/4/29.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public void sendEmail(User user){
        String email = user.getEmail();
        StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://localhost:8080/user?action=activate&email=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(user.getValidateCode());
        sb.append("\">http://localhost:8080/user?action=activate&email=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(user.getValidateCode());
        sb.append("</a>");
        //发送邮件
        SendEmail.send(email, sb.toString());
    }

    public void addUser(User user) {
        sendEmail(user);
        userDao.insert(user);
    }

    public void updateUser(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    public void deleteUserById(int id) {
        userDao.deleteByPrimaryKey(id);
    }

    public User findUserById(int id) {
        return userDao.selectByPrimaryKey(id);
    }

    public User findUserByUserName(String userName) {
        return userDao.selectByForLoginByUserName(userName);
    }

    public List<User> finaAllUsers(int page) {
        return userDao.selectAllUsers((page-1)*10);
    }

    public User findUserByEmail(String email){
        return userDao.selectByEmail(email);
    }

    public String findPasswordByUserName(String userName){
        return userDao.selectPasswordByUserName(userName);
    }

    public Map<String,Object> processActive(String email,String vCode){
        Map<String,Object> result = new HashMap<>();
        User user = userDao.selectByEmail(email);
        if(user == null){
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","该邮箱未注册");
        }else{
            if(user.getActive() == 1) {
                result.put("resultCode", Constant.RETURN_CODE_ERR);
                result.put("msg", "该邮箱已注册");
            }else{
                Date currentTime = new Date();
                Calendar cl = Calendar.getInstance();
                cl.setTime(user.getRegisterTime());
                cl.add(Calendar.DATE,2);
                Date lastActiveTime = cl.getTime();
                if(currentTime.after(lastActiveTime)){
                    result.put("resultCode",Constant.RETURN_CODE_ERR);
                    result.put("msg","该激活码已过期");
                }else {
                    if(vCode.equals(user.getValidateCode())){
                        user.setActive(1);
                        userDao.updateByPrimaryKeySelective(user);
                        result.put("resultCode",Constant.RETURN_CODE_SUCC);
                        result.put("msg","激活成功,正在为你跳转...");
                    }else {
                        result.put("resultCode",Constant.RETURN_CODE_ERR);
                        result.put("msg","激活码错误");
                    }
                }
            }
        }
        return result;
    }

    public User findUserDetailById(int id){
        return userDao.selectDetailUserById(id);
    }
}
