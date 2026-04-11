package com.spring26.section2.group13.movieproductionhouse.alif; // Added .alif here!

import java.io.Serializable;

public class User implements Serializable {
    protected String name;
    protected String email;
    protected String password;

    // This is the constructor that Audience.java is looking for!
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}