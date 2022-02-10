package com.library.LibraryApplication.service;

import com.library.LibraryApplication.entity.Book;
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

    public User getBookById(Long userId) {
        return userRepository.getById(userId);
    }

    public User findUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
