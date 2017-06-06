package com.shadowxz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Post {
    private int id;

    private int sectionId;

    private int userId;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date pulishTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date replyTime;

    private int readNumber;

    private int replyNumber;

    private String content;

    private List<Reply> replies;

    private User postUser;

    public Post(Integer id, Integer sectionId, Integer userId, String title, Date pulishTime, Date replyTime, Integer readNumber, Integer replyNumber) {
        this.id = id;
        this.sectionId = sectionId;
        this.userId = userId;
        this.title = title;
        this.pulishTime = pulishTime;
        this.replyTime = replyTime;
        this.readNumber = readNumber;
        this.replyNumber = replyNumber;
    }

    public Post(Integer sectionId, Integer userId, String title, Date pulishTime, Date replyTime, Integer readNumber, Integer replyNumber, String content) {
        this.sectionId = sectionId;
        this.userId = userId;
        this.title = title;
        this.pulishTime = pulishTime;
        this.replyTime = replyTime;
        this.readNumber = readNumber;
        this.replyNumber = replyNumber;
        this.content = content;
    }



    public Post() {
        super();
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public User getUser() {
        return postUser;
    }

    public void setUser(User user) {
        this.postUser = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getPulishTime() {
        return pulishTime;
    }

    public void setPulishTime(Date pulishTime) {
        this.pulishTime = pulishTime;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public int getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(int readNumber) {
        this.readNumber = readNumber;
    }

    public int getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(int replyNumber) {
        this.replyNumber = replyNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String toString(){
        String post = "Post:" + "[" +
                "id="+ id + "," +
                "sectionId=" + sectionId + "," +
                "userId=" + userId + "," +
                "title=" + title + "," +
                "pulishTime=" + pulishTime + "," +
                "replyTime=" + replyTime + "," +
                "readNumber=" + readNumber + "," +
                "replyNumber=" + replyNumber + "," +
                "content="  + content + "]";
        return post;
    }
}