package com.example.carreview.model;

public class Session {
    String userID;
    String userName;
    String userRole;

    public Session() {
    }

    public Session(String userID, String userName, String userRole) {
        this.userID = userID;
        this.userName = userName;
        this.userRole = userRole;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
