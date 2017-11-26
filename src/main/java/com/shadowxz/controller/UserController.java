package com.shadowxz.controller;

import com.shadowxz.domain.Constant;
import com.shadowxz.domain.Post;
import com.shadowxz.domain.Reply;
import com.shadowxz.domain.User;
import com.shadowxz.service.PostService;
import com.shadowxz.service.ReplyService;
import com.shadowxz.service.UserService;
import com.shadowxz.util.ImageUtil;
import com.shadowxz.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by xz on 2017/4/29.
 */

@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    ReplyService replyService;

    @RequestMapping(value = "/user",method =RequestMethod.POST)
    public @ResponseBody Map<String,Object> postRegisterUser(HttpServletRequest request){
        String action = request.getParameter("action");
        Map<String,Object> result = new HashMap<>();
        if(action.equals("register")){
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            int sex = Integer.parseInt(request.getParameter("sex"));
            String selfIntroduce = request.getParameter("selfIntroduce");
            String validateCode = MD5Util.encode2hex(email);
            User user = userService.findUserByUserName(userName);
            if(user != null){
                result.put("resultCode",Constant.RETURN_CODE_ERR);
                result.put("msg","用户名已存在");
            }else {
                user = userService.findUserByEmail(email);
                if(user != null){
                    result.put("resultCode",Constant.RETURN_CODE_ERR);
                    result.put("msg","该邮箱已注册");
                }else {
                    user = new User(userName, password, email, 0, validateCode, sex, selfIntroduce, 1, new Date(), 0,"30973043-960f-4ca7-8dde-1792af527e20");
                    userService.addUser(user);
                    result.put("resultCode", Constant.RETURN_CODE_SUCC);
                    result.put("msg", "注册成功，请到邮箱激活帐号再登录");
                }
            }
        }else if(action.equals("activate")){
            String email = request.getParameter("email");
            String validateCode = request.getParameter("validateCode");
            result = userService.processActive(email,validateCode);
        }
        return result;
    }

    @RequestMapping(value = "/password",method = RequestMethod.PUT)
    public @ResponseBody Map<String,Object> putPassword(@RequestParam String oldPassword,@RequestParam String newPassword,
                                                           HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Map<String, Object> result = new HashMap<>();
        User user = userService.findUserById(userId);
        String userName = user.getUserName();
        if (oldPassword.equals(userService.findPasswordByUserName(userName))) {
            user.setPassword(newPassword);
            userService.updateUser(user);
            result.put("resultCode", Constant.RETURN_CODE_SUCC);
            result.put("msg", "修改成功");
        } else {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg", "原密码不正确");
        }
        return result;
    }

    @RequestMapping(value = "/email",method = RequestMethod.PUT)
    public @ResponseBody Map<String,Object> sendEmail(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Map<String, Object> result = new HashMap<>();
        User user = userService.findUserById(userId);
        userService.sendEmail(user);
        result.put("resultCode",Constant.RETURN_CODE_SUCC);
        result.put("msg","激活邮件已发到邮箱，请前去验证");
        return result;
    }


    @RequestMapping(value = "/session",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> postUserLogin(@RequestParam String userName, @RequestParam String password,
                                                          HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> result = new HashMap<>();
        User user  = userService.findUserByUserName(userName);
        if(user != null){
            if(user.getPassword().equals(password)){
                if(user.getActive() == 1) {
                    int userId = user.getId();
                    request.getSession().setAttribute("userId",userId);
                    Cookie cookie = new Cookie("userId",Integer.toString(userId));
                    cookie.setPath("/");
                    cookie.setMaxAge(60*60);
                    response.addCookie(cookie);
                    result.put("resultCode", Constant.RETURN_CODE_SUCC);
                    result.put("msg", "登录成功!");
                }else {
                    result.put("resultCode",Constant.RETURN_CODE_ERR);
                    result.put("msg","请先激活邮箱");
                }
            }else {
                result.put("resultCode",Constant.RETURN_CODE_ERR);
                result.put("msg","密码错误");
            }
        }else {
            result.put("resultCode",Constant.RETURN_CODE_ERR);
            result.put("msg","用户不存在");
        }
        return result;
    }

    @RequestMapping(value = "/session",method = RequestMethod.DELETE)
    public @ResponseBody Map<String,Object> userLoginOut(HttpServletRequest request){
        request.getSession().removeAttribute("userId");
        Map<String,Object> result = new HashMap<>();
        result.put("resultCode",Constant.RETURN_CODE_SUCC);
        return result;
    }

    @RequestMapping(value = "/base/{urlUserId}",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getUserInfo(@PathVariable int urlUserId,HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Map<String,Object> result = new HashMap<>();
        if(userId == null){
            result.put("resultCode",Constant.RETURN_CODE_ERR);
        }else {
            result.put("resultCode",Constant.RETURN_CODE_SUCC);
            result.put("user",userService.findUserById(userId));
        }
        return result;
    }

    @RequestMapping(value = "/{urlUserId}",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getUserDetail(@PathVariable int urlUserId,HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        result.put("resultCode",Constant.RETURN_CODE_SUCC);
        result.put("user",userService.findUserDetailById(urlUserId));
        return result;
    }

    @RequestMapping(value = "/{userId}/posts/{page}",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getUserAllPost(@PathVariable int userId,@PathVariable int page){
        List<Post> posts = postService.findPostsByUserId(userId,page);
        Map<String,Object> result = new HashMap<>();
        result.put("posts",posts);
        Integer.valueOf("1");
        return result;
    }

    @RequestMapping(value = "/{userId}/replies/{page}",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getUserAllReply(@PathVariable int userId,@PathVariable int page){
        List<Reply> replies = replyService.findRepliesByUserId(userId,page);
        Map<String,Object> result = new HashMap<>();
        result.put("replies",replies);
        return result;

    }

    @RequestMapping(value = "/{userId}/messages",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getUnreadMessage(@PathVariable int userId){
        Map<String,Object> result = new HashMap<>();
        Map<String,List<Post>> messages = userService.findUnreadMessage(userId);
        result.put("messages",messages);
        return result;
    }

    @RequestMapping(value = "/sex",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> postUserSex(HttpServletRequest request){
        int sex = Integer.parseInt(request.getParameter("sex"));
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        User user = userService.findUserById(userId);
        user.setSex(sex);
        userService.updateUser(user);
        Map<String,Object> result = new HashMap<>();
        result.put("resultCode",Constant.RETURN_CODE_SUCC);
        return result;
    }

    @RequestMapping(value = "/selfIntroduce",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> postUserSelfIntroduce(HttpServletRequest request){
        String selfIntroduce = request.getParameter("selfIntroduce");
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        User user = userService.findUserById(userId);
        user.setSelfIntroduce(selfIntroduce);
        userService.updateUser(user);
        Map<String,Object> result = new HashMap<>();
        result.put("resultCode",Constant.RETURN_CODE_SUCC);
        return result;
    }

    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> uploadUserImage(@RequestParam(value="userImage")MultipartFile image,HttpServletRequest request) throws Exception{
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        User user = userService.findUserById(userId);
        String key = ImageUtil.saveImg(image, "user");
        String url = ImageUtil.getImg(key);
        int begin = url.lastIndexOf("%2F");
        int end = url.indexOf(".");
        url = url.substring(begin+3,end);
        user.setImageUrl(url);
        userService.updateUser(user);
        Map<String,Object> result = new HashMap<>();
        result.put("resultCode",Constant.RETURN_CODE_SUCC);
        result.put("key",url);
        return result;
    }
}
