package com.bourne.caesar.impextutors.Models;

public class User {

    private String userID;
    private String email;
    private String username;


    public User() {
    }

    public User(String userID, String email, String username) {
        this.userID = userID;
        this.email = email;
        this.username = username;

    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

//    public String getDateofBirth() {
//        return dateofBirth;
//    }
}
