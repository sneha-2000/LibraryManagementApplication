package com.library.LibraryApplication.service;

import com.library.LibraryApplication.entity.UserBooking;
import com.library.LibraryApplication.repository.UserBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookingService {

    @Autowired
    UserBookingRepository userBookingRepository;

    public void saveUserBookings(UserBooking userBooking) {
        userBookingRepository.save(userBooking);
    }
    public List<UserBooking> getBookingByUserName(String userName) {
        return userBookingRepository.findUserBookingByUserId(userName);
    }
}
