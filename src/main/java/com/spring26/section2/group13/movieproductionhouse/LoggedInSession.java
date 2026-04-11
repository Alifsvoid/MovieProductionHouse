package com.spring26.section2.group13.movieproductionhouse;

public class LoggedInSession {
    private static LoggedInSession instance;
    private String userID;
    private String userName;
    private String userRole;
    private String email;

    private LoggedInSession() {}

    public static LoggedInSession getInstance() {
        if (instance == null) {
            instance = new LoggedInSession();
        }
        return instance;
    }

    public static void login(String id, String name, String role, String email) {
        getInstance().userID = id;
        getInstance().userName = name;
        getInstance().userRole = role;
        getInstance().email = email;
    }

    public String getUserID() { return userID; }
    public String getUserName() { return userName; }
    public String getUserRole() { return userRole; }
    public String getEmail() { return email; }
}