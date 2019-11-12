package com.example.carreview.model;

public class LikePost {
    String userID;
    String postID;
    String checkLike;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getCheckLike() {
        return checkLike;
    }

    public void setCheckLike(String checkLike) {
        this.checkLike = checkLike;
    }
}
