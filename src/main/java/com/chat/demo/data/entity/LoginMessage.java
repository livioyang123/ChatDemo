package com.chat.demo.data.entity;

public class LoginMessage {
    private String username;
    private String hash_password;
    private String status;

    public LoginMessage(String name, String hpsw){
        this.username = name;
        this.hash_password = hpsw;
    }

    public void setStatus(String s){
        this.status = s;
    }
    public String getStatus(){
        return this.status;
    }
    public String getUserName(){
        return this.username;
    }
    public String getHash_password(){
        return this.hash_password;
    }
}
