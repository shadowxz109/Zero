package com.shadowxz.controller;

import com.shadowxz.domain.Constant;
import com.shadowxz.domain.Post;
import com.shadowxz.domain.Reply;
import com.shadowxz.domain.User;
import com.shadowxz.service.PostService;
import com.shadowxz.service.ReplyService;
import com.shadowxz.service.UserService;
import com.shadowxz.util.Forbid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/view/addPost",method = RequestMethod.GET)
    public ModelAndView addPostView(){return new ModelAndView("main/new");
    }

    @RequestMapping(value = "/view/postInfo",method = RequestMethod.GET)
    public ModelAndView getPostInfoView() {

        return new ModelAndView("main/postInfo");
    }

    @RequestMapping(value ="/view/modifyPost",method = RequestMethod.GET)
    public ModelAndView getModifyPostView(@PathVariable int postId){
        Post post = postService.findPostById(postId);
        Map<String,Object> result = new HashMap<>();
        result.put("title",post.getTitle());
        result.put("content",post.getContent());
        return new ModelAndView("main/new2",result);
    }

    @RequestMapping(value = "/{postId}/page/{page}",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getPost(@PathVariable int postId,@PathVariable int page){
        Post post = postService.findPostById(postId);
        List<Reply> replies = replyService.findRepliesByPostId(postId,page);
        Map<String,Object> result = new HashMap<>();
        if(page == 1)
            postService.increaseReadNumberById(postId);
        result.put("post",post);
        result.put("replies",replies);
        return result;
    }

    @RequestMapping(value ="/{postId}/replies/page/{page}",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> postReply(@PathVariable int postId, @RequestParam String content, HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Map<String,Object> result = new HashMap<>();
        if(userId == null){
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        }else {
            User user = userService.findUserById(userId);
            if(user.getForbidTime() != null) {
                Date now = new Date();
                if (now.before(user.getForbidTime())) {
                    Forbid.checkForbid(user, result);
                }
            }
            Reply reply = new Reply(postId, userId, new Date(),content);
            replyService.addReply(reply);
            result.put("resultCode", Constant.RETURN_CODE_SUCC);
            result.put("msg", "回复成功");
            }
        return result;
    }

    @RequestMapping(value = "/{postId}/reply/{replyId}",method = RequestMethod.DELETE)
    public  @ResponseBody Map<String,Object> deleteReply(@PathVariable int postId,@PathVariable int replyId,HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Map<String,Object> result = new HashMap<>();
        Reply reply = replyService.findReplyById(replyId);
        if(userId == null){
            result.put("resultCode", Constant.RETURN_CODE_ERR);
            result.put("msg","请先登录");
        }else if(userId.equals(reply.getUserId())){
            replyService.deleteReply(reply);
            postService.decreaseReplyNumberById(postId);
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
        Post post = postService.findPostById(postId);
        User user = userService.findUserById(post.getUserId());
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
