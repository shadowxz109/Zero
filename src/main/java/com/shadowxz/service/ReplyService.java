package com.shadowxz.service;

import com.shadowxz.domain.Reply;

import java.util.List;

/**
 * Created by xz on 2017/4/29.
 */
public interface ReplyService {

    void addReply(Reply reply);

    void updateReply(Reply reply);

    void deleteReplyById(int id);

    void deleteReply(Reply reply);

    Reply findReplyById(int id);

    List<Reply> findRepliesByPostId(int postId,int page);

    List<Reply> findRepliesByUserId(int userId,int page);

    List<Reply> findAllReplies(int page);
}
