//package com.library.LibraryApplication.repository;
//
//import com.library.LibraryApplication.entity.AdminUserBooking;
//import com.library.LibraryApplication.entity.UserBooking;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface AdminUserBookingRepository extends JpaRepository<AdminUserBooking,Long> {
//    @Query("Select adminUserBooking from AdminUserBooking adminUserBooking where adminUserBooking.username=:username")
//    List<AdminUserBooking> findUserBookingByUserId(String username);
//}
