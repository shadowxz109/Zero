package com.shadowxz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xz on 2017/5/27.
 */
@Controller
public class ViewController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView getIndex(){
        return new ModelAndView("main/index");
    }

    @RequestMapping(value="/user/signup",method = RequestMethod.GET)
    public ModelAndView getRegisterUserView(){
        return new ModelAndView("main/signup");
    }

    @RequestMapping(value = "/user/signin",method = RequestMethod.GET)
    public ModelAndView getUserLoginView(HttpServletRequest request, ModelAndView modelAndView){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if(userId == null){
            modelAndView.setViewName("main/signin");
        }else {
            modelAndView.setViewName("main/index");
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/post/new",method = RequestMethod.GET)
    public ModelAndView getNewPost(){return new ModelAndView("main/new"); }

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
}
