package com.employeemanagementsystem.empman.Controller;

import com.employeemanagementsystem.empman.Models.JwtRequest;
import com.employeemanagementsystem.empman.Models.JwtResponse;
import com.employeemanagementsystem.empman.jwt.JwtHelper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class JwtAuthenticationController {

        @Autowired
        private UserDetailsService userDetailsService;
        @Autowired
        private AuthenticationManager manager;
        @Autowired
        private JwtHelper helper;

        @PostMapping("/authenticate")
        public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

            this.doAuthenticate(request.getUsername(), request.getPassword());


            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = this.helper.generateToken(userDetails);

            JwtResponse response = JwtResponse.builder()
                    .jwtToken(token)
                    .username(userDetails.getUsername()).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        private void doAuthenticate(String username, String password) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
            try {
                manager.authenticate(authentication);
            } catch (BadCredentialsException e) {
                throw new BadCredentialsException("Credentials Invalid !!");
            }

        }
    }
