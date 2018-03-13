package com.example.demo.vo;

public class MemberVo {

    private int seq;
    private String username;
    private String image;
    
    
    public int getSeq() {
        return seq;
    }
    public void setSeq(int seq) {
        this.seq = seq;
    }
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
        return "MemberVo [seq=" + seq + ", username=" + username + ", image=" + image + "]";
    }
}
