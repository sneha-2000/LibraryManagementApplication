package com.library.LibraryApplication.repository;

import com.library.LibraryApplication.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    //    @Query("Select admin from Admin admin where admin.adminName=:adminName")
    Admin findAdminByUsername(String username);

}
