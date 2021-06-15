package com.example.onlineshopping;

public class Cust {
    private String email;
    private String password;
    private String status;
    private String id;
    public Cust() {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setId(String email) {
        this.id = email;
    }

    public void setStatus(String password) {
        this.status = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public String getId() {
        return id;
    }
    public String getStatus() {
        return status;
    }


}
