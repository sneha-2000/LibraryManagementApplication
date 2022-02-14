package com.library.LibraryApplication.repository;

import com.library.LibraryApplication.entity.UserBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookingRepository extends JpaRepository<UserBooking,Long> {
    @Query("Select userBooking from UserBooking userBooking where userBooking.username=:username")
    List<UserBooking> findUserBookingByUsername(String username);
}
