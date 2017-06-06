package com.shadowxz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Reply {
    private int id;

    private int postId;

    private int userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date replyTime;

    private int state;

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

    public Reply(Integer postId, Integer userId, Date replyTime, String content) {
        this.postId = postId;
        this.userId = userId;
        this.replyTime = replyTime;
        this.content = content;
    }

    public Reply() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
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