package com.library.LibraryApplication.controller;

import com.library.LibraryApplication.entity.User;
import com.library.LibraryApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class UserController {
    static String userName;
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/login")
    public String userRegistration(HttpServletRequest request, Model model) {
        if (!(request.getParameter("password").equals(request.getParameter("confirmPassword")))) {
            model.addAttribute("message", "Password and confirm-password doesn't match!!");
            return "register";
        }
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String city = request.getParameter("city");

        User user = new User(userName,password,email,firstName,lastName,address,phoneNumber,city);
        userService.saveUser(user);
        return "login";
    }

    @PostMapping("/home")
    public String validateUser(HttpServletRequest request, Model model) {
        User user = userService.findUserByUserName(request.getParameter("userName"));
        userName = request.getParameter("userName");
        if (!(Objects.isNull(user))) {

            if ((user.getPassword()).equals(request.getParameter("password"))) {
                return "home";
            } else {
                model.addAttribute("message", "Invalid password!!");
                return "login";
            }
        } else {
            model.addAttribute("message", "Invalid credentials!!");
            return "login";
        }
    }
    @RequestMapping("/home")
    public String home() {
        return "home";
    }




}
