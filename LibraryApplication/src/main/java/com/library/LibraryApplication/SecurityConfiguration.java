//package com.library.LibraryApplication;
//
//import com.library.LibraryApplication.service.UsersUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//   @Autowired
//    private UsersUserDetailsService usersUserDetailsService;
//
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//        //auth.userDetailsService is predefined, userDetailsService is used to retrieve user related data
//        //userDetailsService inside the bracket is we autowired
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/admin").hasRole("admin")
//                .antMatchers("/user").hasAnyRole("admin", "user")//hasAnyRole for multiple roles
//                .antMatchers("/").permitAll()
//                .and().formLogin()
//                .defaultSuccessUrl("/home");
//    }
//
//    @Bean
//    //it saves the password without encoding, not safe
//    public PasswordEncoder getPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
