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

    Post selectByPrimaryKey(Map<String,Object> map);

    Post selectBaseByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Post record);

    //int updateByPrimaryKeyWithBLOBs(Post record);

    int updateByPrimaryKey(Post record);

    int increaseReplyNumberById(Map<String,Object> map);

    int increaseReadNumberById(Map<String,Object> map);

    int decreaseReplyNumberById(Map<String,Object> map);

    List<Post> selectPostsByUserId(Map<String,Object> map);

    List<Post> selectPostsBySectionId(Map<String,Object> map);

    List<Post> selectUnreadRepliesByUserId(int userId);

    List<Post> selectUnreadDialoguesByUserId(int userId);

    List<Post> selectUnreadDialoguesP2PByUserId(int userId);

    List<Post> selectAllPosts(int offset);

    List<Post> selectNewPosts();

    int countPostsNumberBySectionId(int sectionId);

    int countPostsNumber();
}