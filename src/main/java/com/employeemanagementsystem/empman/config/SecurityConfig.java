package com.employeemanagementsystem.empman.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableMethodSecurity
public class SecurityConfig    {

    @Autowired
    private DataSource dataSource ;

    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder() ;
    }

    @Bean

    public UserDetailsService userDetailsService (){
        //In memory Security
        UserDetails normalUser = User.withUsername("Normal")
                .password(passwordEncoder().encode("password"))
                .roles("Emp")
                .build() ;

        UserDetails adminUser = User.withUsername("Admin")
                .password(passwordEncoder().encode("password"))
                .roles("Admin")
                .build()  ;

        return new InMemoryUserDetailsManager(normalUser , adminUser) ;

        //Database Security
      //  return  new CustomUserDetailService ();
    }
//@Bean
//    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception{
//
//          httpSecurity.csrf().disable()
//                  .authorizeHttpRequests()
//                  .requestMatchers("/public/home")
//                  .permitAll()
//                  .anyRequest()
//                  .authenticated()
//                  .and()
//                  .formLogin() ;
//
//          return httpSecurity.build() ;
//
//}




//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .withDefaultSchema()
//                .withUser(User.withUsername("user")
//                        .password(passwordEncoder().encode("pass"))
//                        .roles("USER"));
//    }



}
