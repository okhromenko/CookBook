package com.example.cookbookfinal.Models;


public class User {
    private String email, name, password, image;
    private Boolean role;

    public User(){}

    public User(String email, String name, String password, String image, Boolean role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.image = image;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }
}
