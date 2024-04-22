package com.employeemanagementsystem.empman.auth;

import org.webjars.NotFoundException;
import com.employeemanagementsystem.empman.Config.JwtService;
import com.employeemanagementsystem.empman.Enums.Role;
import com.employeemanagementsystem.empman.Models.User;
import com.employeemanagementsystem.empman.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserRepository repository;

   private final PasswordEncoder passwordEncoder;

   private final JwtService jwtService;

   private final AuthenticationManager authenticationManager;
    public RegistrationResponse register(RegisterRequest request) {
     var user= User.builder()
             .firstname(request.getFirstname())
             .lastname(request.getLastname())
             .email(request.getEmail())
             .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
             repository.save(user);
            // var jwtToken=jwtService.generateToken(user);
             return RegistrationResponse.builder()
                     .message("User Registered Successfully")
                     .HttpStatus(200)
                     .build();
   }

       public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
           try {
               authenticationManager.authenticate(
                       new UsernamePasswordAuthenticationToken(
                               request.getEmail(),
                               request.getPassword()
                       )
               );
               var user = repository.findByEmail(request.getEmail());
               if (user.isPresent()) {
                   User user1 = user.get();
                   var jwtToken = jwtService.generateToken(user1);
                   return AuthenticationResponse.builder()
                           .token(jwtToken)
                           .HttpStatus(200)
                           .message("Token Generated Successfully")
                           .build();
               }else{
                     throw new NotFoundException("User not Found");
               }
           }catch (NotFoundException notFoundException){
                   throw new NotFoundException("Exception not Found");
           }
           catch (Exception exception) {

                   throw new Exception("Exception Found");
           }
       }
}


