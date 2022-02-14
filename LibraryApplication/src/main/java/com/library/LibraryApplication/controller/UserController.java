package com.library.LibraryApplication.controller;

import com.library.LibraryApplication.entity.Admin;
import com.library.LibraryApplication.entity.User;
import com.library.LibraryApplication.service.AdminService;
import com.library.LibraryApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Objects;

@Controller
public class UserController {
    public static Long userId;
    static String username;
    @Autowired
    AdminService adminService;

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

    @PostMapping("/register")
    public String userRegistration(HttpServletRequest request, Model model) {
        if (!(request.getParameter("password").equals(request.getParameter("confirmPassword")))) {
            model.addAttribute("message", "Password and confirm-password doesn't match!!");
            return "register";
        }
        else{
            if(!(((request.getParameter("phoneNumber")).length())==10)){
                model.addAttribute("message", "INVALID PHONE NUMBER!!!");
                return "register";
            }
            else{
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String address = request.getParameter("address");
                String phoneNumber = request.getParameter("phoneNumber");
                String city = request.getParameter("city");

                User user = new User(username, password, email, firstName, lastName, address, phoneNumber, city);
                userService.saveUser(user);
                return "login";
            }
        }

//        return "redirect:/login";// /login

    }
    @GetMapping("/success")
    public String login(Principal principal){
        String username= principal.getName();
        if (username.equals("Admin@111")){
            return "redirect:/adminHome";
        }
        User user = userService.findUserByUsername(username);
        userId = user.getUserId();
        return "redirect:/home";
    }

    @PostMapping("/home")
    public String validateUser(HttpServletRequest request, Model model) {
        User user = userService.findUserByUsername(request.getParameter("username"));
        username = request.getParameter("username");
        if (!(Objects.isNull(user))) {

            if ((user.getPassword()).equals(request.getParameter("password"))) {
                userId=user.getUserId();

                return "home";
            } else {
                model.addAttribute("message", "Invalid password!!");
                return "login";
            }
//        } else if (userName.equals("Admin@123")&&(request.getParameter("password").equals("admin"))) {
//                return "adminHome";

        }
        else {
            Admin admin = adminService.findAdminByUsername(username);
            if (!(Objects.isNull(admin))) {
                if (admin.getPassword().equals(request.getParameter("password"))) {
                    return "adminHome";
                } else {
                    model.addAttribute("message", "Invalid password!!");
                    return "login";
                }
            } else {
                model.addAttribute("message", "Invalid credentials!!");
                return "login";
            }
        }
    }

    @RequestMapping("/home")
    public String home () {
        return "home";
    }

    @RequestMapping("/adminRegister")
    public String adminRegister(){
        return "/adminRegister";
    }

    @PostMapping("/adminLogin")
    public String adminRegistration(HttpServletRequest request, Model model) {

        if (!(request.getParameter("password").equals(request.getParameter("confirmPassword")))) {
            model.addAttribute("message", "Password and confirm-password doesn't match!!");
            return "adminRegister";
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = new Admin(username, password);
        adminService.saveAdmin(admin);

        return "login";

    }

    @RequestMapping("/adminHome")
    public String adminHome() {
        return "adminHome";
    }

}
