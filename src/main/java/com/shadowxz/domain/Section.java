package com.shadowxz.domain;

import java.util.List;

public class Section {
    private Integer id;

    private String sectionName;

    private String introduce;

    private Integer postNumber;

    private List<Post> posts;

    public Section(Integer id, String sectionName, String introduce, Integer postNumber) {
        this.id = id;
        this.sectionName = sectionName;
        this.introduce = introduce;
        this.postNumber = postNumber;
    }

    public Section(String sectionName, String introduce, Integer postNumber) {
        this.sectionName = sectionName;
        this.introduce = introduce;
        this.postNumber = postNumber;
    }

    public Section() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName == null ? null : sectionName.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(Integer postNumber) {
        this.postNumber = postNumber;
    }

    public String toString(){
        String section = "Section:" +"[" +
                "id=" + id +"," +
                "sectionName=" + sectionName +"," +
                "introduce=" + introduce + "," +
                "postTime=" + postNumber +"," +"]";
        return section;
    }
}