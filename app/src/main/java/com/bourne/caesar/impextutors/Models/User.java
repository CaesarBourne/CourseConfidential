package com.bourne.caesar.impextutors.Models;

public class User {

    private String userID;
    private String email;
    private String username;
    private String dateofBirth;

    public User() {
    }

    public User(String userID, String email, String username, String dateofBirth) {
        this.userID = userID;
        this.email = email;
        this.username = username;
        this.dateofBirth = dateofBirth;
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

    public String getDateofBirth() {
        return dateofBirth;
    }
}
