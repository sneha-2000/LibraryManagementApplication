//package com.library.LibraryApplication.service;
//
//import com.library.LibraryApplication.entity.Admin;
//import com.library.LibraryApplication.entity.User;
//import com.library.LibraryApplication.entity.MyUserDetails;
//import com.library.LibraryApplication.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private AdminService adminService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUserName(username);
//        //UserDetails is another entity spring knows, predefined in spring, we don't have to create
//        return new MyUserDetails(user.getUserName(),user.getPassword(),"ROLE_USER");
//
//        if(user==null){
//            Admin admin = adminService.findAdminByAdminName(adminName);
//            return new MyUserDetails(admin.getAdminName(),admin.getPassword(),"ROLE_ADMIN");
//        }
//    }
//}

