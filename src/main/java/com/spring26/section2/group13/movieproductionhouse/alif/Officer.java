package com.spring26.section2.group13.movieproductionhouse.alif;

import java.io.Serializable;

public class Officer implements Serializable {
    private String name;
    private String email;
    private String password;
    private String officerID;

    public Officer(String name, String email, String password, String officerID) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.officerID = officerID;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getOfficerID() { return officerID; }

    @Override
    public String toString() {
        return "Officer{" + "name='" + name + '\'' + ", id='" + officerID + '\'' + '}';
    }
}
