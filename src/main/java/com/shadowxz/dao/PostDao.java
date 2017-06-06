package com.shadowxz.dao;

import com.shadowxz.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PostDao {
    int deleteByPrimaryKey(int id);

    int insert(Post record);

    //int insertSelective(Post record);

    Post selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Post record);

    //int updateByPrimaryKeyWithBLOBs(Post record);

    int updateByPrimaryKey(Post record);

    int increaseReplyNumberById(int id);

    int increaseReadNumberById(int id);

    int decreaseReplyNumberById(int id);

    List<Post> selectPostsByUserId(Map<String,Object> map);

    List<Post> selectPostsBySectionId(Map<String,Object> map);

    List<Post> selectAllPosts(int offset);

    List<Post> selectNewPosts();
}