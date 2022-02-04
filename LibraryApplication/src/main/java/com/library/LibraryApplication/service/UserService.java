package com.library.LibraryApplication.service;

import com.library.LibraryApplication.entity.User;
import com.library.LibraryApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
