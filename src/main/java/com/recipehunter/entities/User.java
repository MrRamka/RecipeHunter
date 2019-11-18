package com.recipehunter.entities;
import com.recipehunter.entities.UserRole;
public class User {
    private String name;
    private String email;
    private String password;
    private UserRole role;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public User(String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }


}
