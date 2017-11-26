package com.shadowxz.service;

import com.shadowxz.domain.Post;

import java.util.List;

/**
 * Created by xz on 2017/4/29.
 */
public interface PostService {

    void addPost(Post post);

    void updatePost(Post post);

    void deletePostByPost(Post post);

    void deletePostById(int id);

    Post findPostById(int id,int page);

    Post findBasePostById(int id);

    List<Post> findPostsBySectionId(int sectionId,int page);

    List<Post> findAllPosts(int page);

    List<Post> findPostsByUserId(int userId,int page);

    void increaseReplyNumberById(int id,int number);

    void decreaseReplyNumberById(int id,int number);

    void increaseReadNumberById(int id,int number);

    List<Post> findNewPosts();

    int findPostsPageCountBySectionId(int sectionId);

}
