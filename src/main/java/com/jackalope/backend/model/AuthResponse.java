package com.jackalope.backend.model;

public class AuthResponse {
    private String message;
    private User user;
    private boolean success;

    public AuthResponse() {}

    public AuthResponse(String message, User user, boolean success) {
        this.message = message;
        this.user = user;
        this.success = success;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}