package com.shadowxz.service.Impl;

import com.shadowxz.dao.PostDao;
import com.shadowxz.dao.ReplyDao;
import com.shadowxz.domain.Post;
import com.shadowxz.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xz on 2017/4/29.
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired ReplyDao replyDao;

    public void addPost(Post post) {
        postDao.insert(post);
    }

    public void updatePost(Post post) {
        postDao.updateByPrimaryKey(post);
    }

    public void deletePostByPost(Post post) {
        int id = post.getId();
        postDao.deleteByPrimaryKey(id);
    }

    public void deletePostById(int id) {
        postDao.deleteByPrimaryKey(id);
        replyDao.deleteRepliesByPostId(id);
    }

    public void increaseReplyNumberById(int id,int number){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("number",number);
        postDao.increaseReplyNumberById(map);
    }

    public void decreaseReplyNumberById(int id,int number){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("number",number);
        postDao.decreaseReplyNumberById(map);
    }

    public void increaseReadNumberById(int id,int number){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("number",number);
        postDao.increaseReadNumberById(map);
    }

    public Post findPostById(int id,int page) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("offset",(page-1)*10);
        Post post = postDao.selectByPrimaryKey(map);
        if(post == null){
            post = postDao.selectBaseByPrimaryKey(id);
        }
        return post;
    }

    public Post findBasePostById(int id){
        return postDao.selectBaseByPrimaryKey(id);
    }

    public List<Post> findPostsBySectionId(int sectionId,int page) {
        Map<String,Object> map = new HashMap<>();
        map.put("sectionId",sectionId);
        map.put("offset",(page-1)*10);
        List<Post> posts = postDao.selectPostsBySectionId(map);
        if(sectionId == 1){
            posts = findAllPosts(page);
        }
        return posts;
    }

    public List<Post> findPostsByUserId(int userId,int page) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("offset",(page-1)*10);
        return postDao.selectPostsByUserId(map);
    }

    public List<Post> findAllPosts(int page){return postDao.selectAllPosts((page-1)*10);}

    public List<Post> findNewPosts(){
        return postDao.selectNewPosts();
    };

    public int findPostsPageCountBySectionId(int sectionId){
        int postCount = 1;
        if(sectionId == 1)
            postCount = postDao.countPostsNumber();
        else postCount =  postDao.countPostsNumberBySectionId(sectionId);
        return ((postCount-1)/10+1);
    }
}
