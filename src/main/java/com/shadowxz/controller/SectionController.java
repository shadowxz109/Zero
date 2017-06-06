package com.shadowxz.controller;

import com.shadowxz.domain.*;
import com.shadowxz.service.PostService;
import com.shadowxz.service.ReplyService;
import com.shadowxz.service.SectionService;
import com.shadowxz.service.UserService;
import com.shadowxz.util.Forbid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xz on 2017/4/29.
 */

@Controller
@RequestMapping(value = "/api/section")
public class SectionController {

    @Autowired
    SectionService sectionService;

    @Autowired
    PostService postService;

    @Autowired
    ReplyService replyService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/{sectionId}/posts/page/{page}",method = RequestMethod.GET)
    public @ResponseBody Map<String,List<?>> getPostsBySection(@PathVariable int sectionId,@PathVariable int page){
        List<Post> posts = postService.findPostsBySectionId(sectionId,page);
        List<Section> sections = sectionService.findAllSection();
        Map<String,List<?>> result = new HashMap<>();
        result.put("sections",sections);
        result.put("posts",posts);
        return result;
    }

    @RequestMapping(value = "/post/{postId}",method = RequestMethod.PUT)
    public @ResponseBody Map<String,Object> updatePost(@PathVariable int postId, HttpServletRequest request,
                                                       @RequestParam String title,@RequestParam String content){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Map<String,Object> result = new HashMap<String,Object>();
        Post post = postService.findPostById(postId);
        post.setTitle(title);
        post.setContent(content);
        if(userId == null){
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        }
        else if(userId.equals(post.getUserId())){
            postService.updatePost(post);
            result.put("resultCode", Constant.RETURN_CODE_SUCC);
            result.put("msg","修改成功");
        }else {
            result.put("resultCode",Constant.RETURN_CODE_ERR);
            result.put("msg","你不能修改他人发的帖子");
        }
        return result;
    }

    @RequestMapping(value = "/post/{postId}",method = RequestMethod.DELETE)
    public @ResponseBody Map<String,Object> deletePost(@PathVariable int postId,HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Map<String,Object> result = new HashMap<>();
        Post post = postService.findPostById(postId);
        if(userId==null) {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        }
        else if(userId.equals(post.getUserId())) {
            postService.deletePostById(postId);
            result.put("resultCode", Constant.RETURN_CODE_SUCC);
            result.put("msg","删除成功");
        }else {
            result.put("resultCode",Constant.RETURN_CODE_ERR);
            result.put("msg","你不能删除他人发的帖子");
        }
        return result;
    }

    @RequestMapping(value = "/{sectionId}/post",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> commitPost(@PathVariable int sectionId,HttpServletRequest request,
                                                       @RequestParam String title,@RequestParam String content){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Map<String,Object> result = new HashMap<>();
        if(userId==null){
            result.put("resultCode",Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        } else {
            User user = userService.findUserById(userId);
            if(user.getForbidTime() != null) {
                Date now = new Date();
                if (now.before(user.getForbidTime())) {
                    Forbid.checkForbid(user, result);
                }
            }else {
                Post post = new Post(sectionId,userId,title,new Date(),new Date(),0,0,content);
                postService.addPost(post);
                result.put("resultCode", Constant.RETURN_CODE_SUCC);
                result.put("msg", "创建帖子成功");
            }
        }
        return result;
    }

    @RequestMapping(value = "/sections",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getSections(){
        List<Section> sections = sectionService.findAllSection();
        Map<String,Object> result = new HashMap<>();
        result.put("sections",sections);
        return result;
    }
}
