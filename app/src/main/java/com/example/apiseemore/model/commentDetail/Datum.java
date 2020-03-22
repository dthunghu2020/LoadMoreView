package com.example.apiseemore.model.commentDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("sender_id")
    @Expose
    private Integer senderId;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("attachfile")
    @Expose
    private Object attachfile;
    @SerializedName("create_time")
    @Expose
    private String createTime;
    @SerializedName("count_liked")
    @Expose
    private Integer countLiked;
    @SerializedName("liked")
    @Expose
    private Boolean liked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getAttachfile() {
        return attachfile;
    }

    public void setAttachfile(Object attachfile) {
        this.attachfile = attachfile;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCountLiked() {
        return countLiked;
    }

    public void setCountLiked(Integer countLiked) {
        this.countLiked = countLiked;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

}
