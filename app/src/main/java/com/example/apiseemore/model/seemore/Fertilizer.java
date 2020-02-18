package com.example.apiseemore.model.seemore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fertilizer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("component")
    @Expose
    private String component;
    @SerializedName("uses")
    @Expose
    private Object uses;
    @SerializedName("user_manual")
    @Expose
    private String userManual;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("pivot")
    @Expose
    private Pivot_ pivot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Object getUses() {
        return uses;
    }

    public void setUses(Object uses) {
        this.uses = uses;
    }

    public String getUserManual() {
        return userManual;
    }

    public void setUserManual(String userManual) {
        this.userManual = userManual;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pivot_ getPivot() {
        return pivot;
    }

    public void setPivot(Pivot_ pivot) {
        this.pivot = pivot;
    }

}