package com.in28minutes.learnspringsecurity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
//import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_info")
public class UserInfo {
    @Id
    private String name;
    private String password;
    private String roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
