package com.shadowxz.controller;

import com.shadowxz.domain.Constant;
import com.shadowxz.domain.Post;
import com.shadowxz.domain.Reply;
import com.shadowxz.service.PostService;
import com.shadowxz.service.ReplyService;
import com.shadowxz.service.UserService;
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
@RequestMapping(value = "/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    ReplyService replyService;


    @RequestMapping(value ="/{postId}/replies/page/{page}",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> postReply(@PathVariable int postId, @RequestParam String content, HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Map<String,Object> result = new HashMap<>();
        Reply reply = new Reply(postId, userId, new Date(),content,0);
        replyService.addReply(reply);
        result.put("resultCode", Constant.RETURN_CODE_SUCC);
        result.put("msg", "回复成功");
        return result;
    }

    @RequestMapping(value = "/reply/{replyId}",method = RequestMethod.DELETE)
    public  @ResponseBody Map<String,Object> deleteReply(@PathVariable int replyId,HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Map<String,Object> result = new HashMap<>();
        Reply reply = replyService.findReplyById(replyId);
        if(userId.equals(reply.getUserId())){
            replyService.deleteReply(reply);
            result.put("resultCode",Constant.RETURN_CODE_SUCC);
            result.put("msg","删除成功");
        }else {
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","你不能删除他人的回复哟");
        }
        return result;
    }

    @RequestMapping(value = "/{postId}/replies/page/{page}",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getUserAllReply(@PathVariable int postId,@PathVariable int page){
        Map<String,Object> result = new HashMap<>();
        if(page == 1)
            postService.increaseReadNumberById(postId);
        Post post = postService.findPostById(postId,page);
        result.put("post",post);
        return result;
    }

    @RequestMapping(value = "/newPosts",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getNewPosts(){
        Map<String,Object> result = new HashMap<>();
        List<Post> posts = postService.findNewPosts();
        result.put("posts",posts);
        return result;
    }
}
