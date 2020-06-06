package com.guru84.todo.app.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    
	@JsonProperty(value = "username")
    private String username;

    private String password;
    
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }

    public void setUsernameOrEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}