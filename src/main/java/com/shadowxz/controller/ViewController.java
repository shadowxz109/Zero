package com.shadowxz.controller;

import com.shadowxz.domain.Constant;
import com.shadowxz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xz on 2017/5/27.
 */
@Controller
public class ViewController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView getIndex(){
        return new ModelAndView("main/index");
    }

    @RequestMapping(value="/user/signup",method = RequestMethod.GET)
    public ModelAndView getRegisterUserView(){
        return new ModelAndView("main/signup");
    }

    @RequestMapping(value = "/user/signin",method = RequestMethod.GET)
    public ModelAndView getUserLoginView(HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if(userId == null){
            return new ModelAndView("main/signin");
        }else {
            return new ModelAndView("main/index");
        }
    }
    
    @RequestMapping(value = "/post/new",method = RequestMethod.GET)
    public ModelAndView getNewPost(HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if(userId == null){
            return new ModelAndView("main/signin");
        }else {
            return new ModelAndView("main/new");
        }
    }

    @RequestMapping(value = "/post/{postId}/replies/page/{page}",method = RequestMethod.GET)
    public ModelAndView getPost(){
        return new ModelAndView("main/post");
    }

    @RequestMapping(value = "/section/{sectionId}/posts/page/{page}",method = RequestMethod.GET)
    public ModelAndView getPostsBySection(){
       return new ModelAndView("main/index");
    }

    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
    public ModelAndView getUserInfo(){
        return new ModelAndView("main/user");
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ModelAndView activeUser(HttpServletRequest request){
        return new ModelAndView("main/active");
    }

    @RequestMapping(value = "/unlogin",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> unLogin(){
        Map<String,Object> result = new HashMap<>();
        result.put("resultCode", Constant.RETURN_CODE_NOLOGIN);
        result.put("msg","请先登录");
        return result;
    }
}
