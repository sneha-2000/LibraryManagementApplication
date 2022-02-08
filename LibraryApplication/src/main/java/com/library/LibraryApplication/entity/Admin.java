package com.library.LibraryApplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
    @Id
    private Long id;
    @Column(unique = true, nullable = false)
    private String adminName;
    @Column
    private String password;

    public Admin() {
    }

    public Admin(Long id, String adminName, String password) {
        this.id = id;
        this.adminName = adminName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
