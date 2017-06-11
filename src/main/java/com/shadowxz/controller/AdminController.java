package com.shadowxz.controller;

import com.shadowxz.domain.*;
import com.shadowxz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xz on 2017/4/29.
 */

@Controller
@RequestMapping(value = "/qsgctys")
public class AdminController {
    
    @Autowired
    AdminService adminService;

    @Autowired
    ReplyService replyService;

    @Autowired
    PostService postService;

    @Autowired
    SectionService sectionService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView getIndex(HttpServletRequest request){
        return getLogin(request);
    }

    @RequestMapping(value = "/view/signup",method = RequestMethod.GET)
    public ModelAndView getLoginView(){
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/sesstion",method = RequestMethod.GET)
    public ModelAndView getLogin(HttpServletRequest request){
        Integer sesstionId = (Integer) request.getSession().getAttribute("sesstionId");
        if(sesstionId == null){
            return new ModelAndView("admin/login");
        }else {
            return new ModelAndView("admin/index");
        }
    }

    @RequestMapping(value = "/sesstion",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> postLogin(@RequestParam String userName, @RequestParam String password, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        Admin admin = adminService.findByUserName(userName);
        if(admin != null) {
            if(admin.getPassword().equals(password.trim())){
                request.getSession().setAttribute("sesstionId",admin.getId());
                result.put("resultCode", Constant.RETURN_CODE_SUCC);
                result.put("msg","登录成功！");
            }else{
                result.put("resultCode",Constant.RETURN_CODE_ERR);
                result.put("msg","密码错误！");
            }
        }else{
            result.put("resultCode",Constant.RETURN_CODE_ERR);
            result.put("msg","用户不存在！");
        }
        return result;
    }

    @RequestMapping(value = "/sesstion",method = RequestMethod.DELETE)
    public ModelAndView loginOut(HttpServletRequest request,ModelAndView modelAndView){
        if (request.getSession().getAttribute("sesstionId") != null) {
            request.getSession().removeAttribute("sesstionId");
        }
        modelAndView.setViewName("admin/login");
        return modelAndView;
    }

    //不会返回超级管理员的信息
    @RequestMapping(value = "/admin/admins/{page}",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getAllAdmins(HttpServletRequest request,@PathVariable int page){
        Integer sesstionId = (Integer) request.getSession().getAttribute("sesstionId");
        Map<String,Object> result = new HashMap<>();
        if(sesstionId == null) {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        } else{
            Admin admin = adminService.findById(sesstionId);
            if(admin.getState() == 1){
                result.put("resultCode", Constant.RETURN_CODE_SUCC);
                result.put("msg","删除管理员成功");
                result.put("admins",adminService.findAllAdmins(page));
            }else {
                result.put("resultCode",Constant.RETURN_CODE_ERR);
                result.put("msg","你没有该权限");
            }
        }
        return result;
    }

    @RequestMapping(value = "/admin",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> postAdmin(@RequestParam String userName,@RequestParam String password,HttpServletRequest request){
        Integer sesstionId = (Integer) request.getSession().getAttribute("sesstionId");
        Map<String,Object> result = new HashMap<>();
        if(sesstionId == null) {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        } else{
            Admin admin = adminService.findById(sesstionId);
            if(admin.getState() == 1){
                Admin newAdmin = new Admin(userName,password);
                adminService.addAdmin(newAdmin);
                result.put("resultCode", Constant.RETURN_CODE_SUCC);
                result.put("msg","添加管理员成功");
            }else {
                result.put("resultCode",Constant.RETURN_CODE_ERR);
                result.put("msg","你没有添加该权限");
            }
        }
        return result;
        /*
        * Admin newAdmin = new Admin(userName,password);
        * adminService.addAdmin(newAdmin);
        *
        */
    }

    @RequestMapping(value = "/admin/{adminId}",method = RequestMethod.DELETE)
    public @ResponseBody Map<String,Object> deleteAdmin(@PathVariable int adminId,HttpServletRequest request){
        Integer sesstionId = (Integer) request.getSession().getAttribute("sesstionId");
        Map<String,Object> result = new HashMap<>();
        if(sesstionId == null) {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        } else{
            Admin admin = adminService.findById(adminId);
            if(admin.getState() == 1){
                adminService.deleteAdmin(adminId);
                result.put("resultCode", Constant.RETURN_CODE_SUCC);
                result.put("msg","删除管理员成功");
            }else {
                result.put("resultCode",Constant.RETURN_CODE_ERR);
                result.put("msg","你没有该权限");
            }
        }
        return result;
    }

    @RequestMapping(value = "/section/sections",method = RequestMethod.GET)
    public @ResponseBody List<Section> getAllSections(){
        return sectionService.findAllSection();
    }

    @RequestMapping(value = "/section/{sectionId}",method = RequestMethod.GET)
    public @ResponseBody Section getSection(@PathVariable int sectionId){
        return sectionService.findSectionById(sectionId);
    }

    @RequestMapping(value = "/section/{sectionId}",method = RequestMethod.DELETE)
    public @ResponseBody Map<String,Object> deleteSection(@PathVariable int sectionId, HttpServletRequest request){
        Integer sesstionId = (Integer) request.getSession().getAttribute("sesstionId");
        Map<String,Object> result = new HashMap<>();
        if(sesstionId == null) {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        } else {
            sectionService.deleteSectionById(sectionId);
            result.put("resultCode", Constant.RETURN_CODE_SUCC);
            result.put("msg","删除成功");
        }
        return result;
    }

    //也有点问题
    @RequestMapping(value = "/section/modifySection",method = RequestMethod.GET)
    public ModelAndView getModifySection(ModelAndView modelAndView){
        modelAndView.setViewName("modifySection");
        return modelAndView;
    }

    @RequestMapping(value = "/section/{sectionId}",method = RequestMethod.PUT)
    public @ResponseBody Map<String,Object> updateSection(@RequestParam String sectionName,@RequestParam String introduce){
        Map<String,Object> result = new HashMap<>();
        Section section = new Section(sectionName,introduce,0);
        sectionService.updateSection(section);
        result.put("resultCode", Constant.RETURN_CODE_SUCC);
        result.put("msg","");
        return result;
    }

    @RequestMapping(value = "/section/view/addSection",method = RequestMethod.GET)
    public ModelAndView addSection(){
        return new ModelAndView("addSection");
    }

    @RequestMapping(value = "/section",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> postAddSection(Section section,HttpServletRequest request){
        Integer sesstionId = (Integer) request.getSession().getAttribute("sesstionId");
        Map<String,Object> result = new HashMap<>();
        if(sesstionId == null) {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        }else{
            sectionService.addSection(section);
            result.put("resultCode", Constant.RETURN_CODE_SUCC);
            result.put("msg","添加成功");
        }
        return result;
    }

    @RequestMapping(value = "/user/users/{page}",method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers(@PathVariable int page){
        return userService.finaAllUsers(page);
    }

    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable int userId){
        return userService.findUserById(userId);
    }

    @RequestMapping(value = "/user/{userId}",method = RequestMethod.PUT)
    public @ResponseBody Map<String,Object> updateUser(HttpServletRequest request,@PathVariable int userId,@RequestParam int days){
        Integer sesstionId = (Integer) request.getSession().getAttribute("sesstionId");
        Map<String,Object> result = new HashMap<>();
        if(sesstionId == null) {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        }else{
            User user = userService.findUserById(userId);
            user.setForbidTime(days);
            result.put("resultCode", Constant.RETURN_CODE_SUCC);
            result.put("msg","禁言成功");
        }
        return result;
    }

    @RequestMapping(value = "/user/{userId}",method = RequestMethod.DELETE)
    public @ResponseBody Map<String,Object> deleteUser(@PathVariable int userId, HttpServletRequest request){
        Integer sesstionId = (Integer) request.getSession().getAttribute("sesstionId");
        Map<String,Object> result = new HashMap<>();
        if(sesstionId == null) {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        } else {
            userService.deleteUserById(userId);
            result.put("resultCode", Constant.RETURN_CODE_SUCC);
            result.put("msg","删除成功");
        }
        return result;
    }

    @RequestMapping(value = "/post/posts/{page}",method = RequestMethod.GET)
    public @ResponseBody List<Post> getAllPosts(@PathVariable int page){
        return postService.findAllPosts(page);
    }

    @RequestMapping(value = "/post/{postId}",method = RequestMethod.GET)
    public @ResponseBody Post getPost(@PathVariable int postId){
        return postService.findBasePostById(postId);
    }

    @RequestMapping(value = "/post/{postId}",method = RequestMethod.DELETE)
    public @ResponseBody Map<String,Object> deletePost(@RequestParam int postId,HttpServletRequest request){
        Integer sesstionId = (Integer) request.getSession().getAttribute("sesstionId");
        Map<String,Object> result = new HashMap<>();
        if(sesstionId == null) {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        } else {
            postService.deletePostById(postId);
            result.put("resultCode", Constant.RETURN_CODE_SUCC);
            result.put("msg","删除成功");
        }
        return result;
    }

    @RequestMapping(value = "/reply/replies/{page}",method = RequestMethod.GET)
    public @ResponseBody List<Reply> getAllReplies(@PathVariable int page){
        return replyService.findAllReplies(page);
    }

    @RequestMapping(value = "reply/{replyId}",method = RequestMethod.DELETE)
    public @ResponseBody Map<String,Object> deleteReply(@PathVariable int replyId,HttpServletRequest request){
        Integer sesstionId = (Integer) request.getSession().getAttribute("sesstionId");
        Map<String,Object> result = new HashMap<>();
        if(sesstionId == null) {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        } else {
            replyService.deleteReplyById(replyId);
            result.put("resultCode", Constant.RETURN_CODE_SUCC);
            result.put("msg","删除成功");
        }
        return result;
    }


}
