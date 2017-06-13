package com.shadowxz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Reply {
    private Integer id;

    private Integer postId;

    private Integer userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date replyTime;

    private Integer state;

    private String content;

    public User getUser() {
        return replyUser;
    }

    public void setUser(User user) {
        this.replyUser = user;
    }

    private  User replyUser;

    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Reply(Integer id, Integer postId, Integer userId, Date replyTime, Integer state, String content) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.replyTime = replyTime;
        this.state = state;
        this.content = content;
    }

    public Reply(Integer postId, Integer userId, Date replyTime, String content,Integer state) {
        this.postId = postId;
        this.userId = userId;
        this.replyTime = replyTime;
        this.content = content;
        this.state = state;
    }

    public Reply() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String toString(){
        String reply = "Reply:" +"[" +
                "id=" + id +"," +
                "postId=" + postId +"," +
                "userId=" + userId + "," +
                "replyTime=" + replyTime +"," +
                "state=" + state + "," +
                "content=" + content +"," +"]";
        return reply;
    }
}