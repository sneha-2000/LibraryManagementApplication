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

    /**
     * Index page set as login.
     * @return
     */

    @RequestMapping("/")
    public String index() {
        return "login";
    }

    /**
     * registering of user.
     * @return
     */

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     *
     * @param request user inputs for registration.
     * @param model if input validations violated.
     * @return
     */

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

    }

    /**
     * To check whether it is a user page or admin page after login.
     * @param principal get username or admin username.
     * @return
     */

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

    /**
     * admin registration.
     * @param request username, password and confirm password from admin.
     * @param model error message.
     * @return
     */

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
