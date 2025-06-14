package com.glisterbyte.Network;

public class Credentials {
    private String username;
    private String password;
    private String type;
    public Credentials(String[] loginPair) {
        username = loginPair[0];
        password = loginPair[1];
    }
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
        this.type = "email";
    }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
