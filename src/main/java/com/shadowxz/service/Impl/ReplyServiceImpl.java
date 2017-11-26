package com.shadowxz.service.Impl;

import com.shadowxz.dao.ReplyDao;
import com.shadowxz.domain.Post;
import com.shadowxz.domain.Reply;
import com.shadowxz.service.PostService;
import com.shadowxz.service.ReplyService;
import com.shadowxz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xz on 2017/4/29.
 */

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    ReplyDao replyDao;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    public void addReply(Reply reply) {
        int postId = reply.getPostId();
        postService.increaseReplyNumberById(postId,1);
        Post post = postService.findBasePostById(postId);
        int userId = post.getUserId();
        userService.updateMessageNumberById(userId,1);
        post.setReplyTime(new Date());
        postService.updatePost(post);
        replyDao.insert(reply);
    }

    public void updateReply(Reply reply) {
        replyDao.updateByPrimaryKeySelective(reply);
    }

    public void deleteReply(Reply reply){
        int postId = reply.getPostId();
        postService.decreaseReplyNumberById(postId,1);
        int replyId = reply.getId();
        replyDao.deleteByPrimaryKey(replyId);
    }

    public void deleteReplyById(int id) {
        replyDao.deleteByPrimaryKey(id);
    }

    public Reply findReplyById(int id) {
        return replyDao.selectByPrimaryKey(id);
    }

    public List<Reply> findRepliesByPostId(int postId,int page) {
        Map<String,Object> map = new HashMap<>();
        map.put("postId",postId);
        map.put("offset",(page-1)*10);
        return replyDao.selectRepliesByPostId(map);
    }

    public List<Reply> findRepliesByUserId(int userId,int page) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("offset",(page-1)*10);
        return replyDao.selectRepliesByUserId(map);
    }

    public List<Reply> findAllReplies(int page){return replyDao.selectAllRepies((page-1)*10);}


    public int findReplyPageCountByPostId(int postId){
        int repliesNum = replyDao.countRepliesNumberByPostId(postId);
        return ((repliesNum-1)/10+1);
    }
}
