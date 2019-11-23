package com.recipehunter.entities;
import com.recipehunter.entities.UserRole;
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String salt;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getSalt() {
        return salt;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public User(int id, String name, String email, String password, String role, String salt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.salt = salt;
    }


}
