package com.library.LibraryApplication.service;

import com.library.LibraryApplication.entity.Admin;
import com.library.LibraryApplication.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    public Admin findAdminByUsername(String username) {
        return adminRepository.findAdminByUsername(username);
    }
}
