package com.library.LibraryApplication.service;

import com.library.LibraryApplication.entity.AdminUserBooking;
import com.library.LibraryApplication.entity.UserBooking;
import com.library.LibraryApplication.repository.AdminUserBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserBookingService {

    @Autowired
    AdminUserBookingRepository adminUserBookingRepository;

    public void saveAdminUserBooking(AdminUserBooking adminUserBooking) {
        adminUserBookingRepository.save(adminUserBooking);
    }
    public List<AdminUserBooking> getBookingByUserName(String userName) {
        return adminUserBookingRepository.findUserBookingByUserId(userName);
    }
}
