package com.example.apiseemore.model.seemore;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("count_comments")
    @Expose
    private Integer countComments;
    @SerializedName("count_liked")
    @Expose
    private Integer countLiked;
    @SerializedName("liked")
    @Expose
    private Boolean liked;
    @SerializedName("pests")
    @Expose
    private List<Pest> pests = null;
    @SerializedName("fertilizers")
    @Expose
    private List<Fertilizer> fertilizers = null;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("transporters")
    @Expose
    private List<Transporter> transporters = null;
    @SerializedName("packages")
    @Expose
    private List<Package> packages = null;

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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCountComments() {
        return countComments;
    }

    public void setCountComments(Integer countComments) {
        this.countComments = countComments;
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

    public List<Pest> getPests() {
        return pests;
    }

    public void setPests(List<Pest> pests) {
        this.pests = pests;
    }

    public List<Fertilizer> getFertilizers() {
        return fertilizers;
    }

    public void setFertilizers(List<Fertilizer> fertilizers) {
        this.fertilizers = fertilizers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Transporter> getTransporters() {
        return transporters;
    }

    public void setTransporters(List<Transporter> transporters) {
        this.transporters = transporters;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

}