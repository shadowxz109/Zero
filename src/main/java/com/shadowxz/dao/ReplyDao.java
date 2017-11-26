package com.shadowxz.dao;

import com.shadowxz.domain.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReplyDao {
    int deleteByPrimaryKey(int id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);

    List<Reply> selectRepliesByPostId(Map<String,Object> map);

    List<Reply> selectRepliesByUserId(Map<String,Object> map);

    List<Reply> selectAllRepies(int offset);

    void deleteRepliesByPostId(int postId);

    int countRepliesNumberByPostId(int postId);
}