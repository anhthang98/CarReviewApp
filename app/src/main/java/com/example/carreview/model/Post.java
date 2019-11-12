package com.example.carreview.model;

import java.io.Serializable;

public class Post implements Serializable {
    String postID;
    String userId;
    String carName;
    String carPrice;
    String carBrand;
    String carType;
    String imageBefore;
    String imageAfter;
    String imageLeft;
    String imageRight;
    String totalLike;
    String description;

    public String getPostID() {
        return postID;
    }

    public void setPostId(String postId) {
        this.postID = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String title) {
        this.carName = title;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getImageBefore() {
        return imageBefore;
    }

    public void setImageBefore(String imageBefore) {
        this.imageBefore = imageBefore;
    }

    public String getImageAfter() {
        return imageAfter;
    }

    public void setImageAfter(String imageAfter) {
        this.imageAfter = imageAfter;
    }

    public String getImageLeft() {
        return imageLeft;
    }

    public void setImageLeft(String imageLeft) {
        this.imageLeft = imageLeft;
    }

    public String getImageRight() {
        return imageRight;
    }

    public void setImageRight(String imageRight) {
        this.imageRight = imageRight;
    }

    public String getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(String totalLike) {
        this.totalLike = totalLike;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
