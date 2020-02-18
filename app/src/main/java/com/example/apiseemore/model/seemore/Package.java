package com.example.apiseemore.model.seemore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("picture")
    @Expose
    private Object picture;
    @SerializedName("specification")
    @Expose
    private String specification;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("pivot")
    @Expose
    private Pivot____ pivot;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pivot____ getPivot() {
        return pivot;
    }

    public void setPivot(Pivot____ pivot) {
        this.pivot = pivot;
    }

}