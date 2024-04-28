package com.cookie.todolist.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Credential {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public Credential() {
    }

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

}
