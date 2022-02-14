package com.library.LibraryApplication;

import com.library.LibraryApplication.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }


//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager(user);
    //auth.userDetailsService is predefined, userDetailsService is used to retrieve user related data
    //userDetailsService inside the bracket is we autowired

    @Bean
    //it saves the password without encoding, not safe
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/adminHome","/addBooks","/adminBookList","/adminIssueList","/adminUserBooking",
                       "adminUserList","/bookIssueDatesByAdmin").hasRole("ADMIN")
//                .antMatchers("/user").hasAnyRole("admin", "user")//hasAnyRole for multiple roles
                .antMatchers("/home","/userBookDetails","/userBookingDetails","/userIssueBook/*","/userPayment",
                        "/successfulPayment").hasRole("USER")
                .antMatchers("/").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/success");
//    }

//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/adminHome").hasRole("ADMIN")
//                .antMatchers("/").permitAll()
//                .and().formLogin()
//                .loginPage("/login");

    }
}
