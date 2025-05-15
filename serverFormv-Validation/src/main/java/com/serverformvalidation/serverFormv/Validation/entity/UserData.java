package com.serverformvalidation.serverFormv.Validation.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;

@Entity
public class UserData {
    @NotBlank(message = "User Nmae ca not be empty!!")
    @Size(min = 3,max = 12,message = "User name must be between 3 - 12 characters !")
    private String userName;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "invalid email")
    private String email;

    @AssertTrue(message = "Must aagree terms and agreements ")
    private boolean agreed;


    public  UserData(){

    }

    public UserData(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
