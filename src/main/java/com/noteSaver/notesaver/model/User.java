package com.noteSaver.notesaver.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;
    @Id
    @NotBlank
    private String email;

    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String contentEmail) {
        email = contentEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordData) {
        password = passwordData;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String roleData) {
        role = roleData;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userNameData) {
        userName = userNameData;
    }
}