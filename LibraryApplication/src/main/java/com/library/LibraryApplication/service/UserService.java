package com.library.LibraryApplication.service;

import com.library.LibraryApplication.entity.Book;
import com.library.LibraryApplication.entity.User;
import com.library.LibraryApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.getById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
