package com.example.demo.vo;

public class MemberVo {

    private String username;
    private String image;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    @Override
    public String toString() {
        return "MemberVo [username=" + username + ", image=" + image + "]";
    }
}
