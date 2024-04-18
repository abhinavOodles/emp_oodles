package com.employeemanagementsystem.empman.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper ;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

      final   String requestHandler = request.getHeader("Authorization") ;
        log.info("header: {}" , requestHandler);
        String username = null;
        String token = null ;

        if (requestHandler != null && requestHandler.startsWith("Bearer")){
            token = requestHandler.substring(7);
            try{
                username = this.jwtHelper.getUsernameFromToken(token) ;
                         }
            catch (IllegalArgumentException e){
                log.info("illegal argument while fetching the username !!") ;
                e.printStackTrace();
            }
            catch (ExpiredJwtException e){
                log.info("token is expired");
                e.printStackTrace();
            }
            catch (MalformedJwtException e){
                log.info("Some changes is done in the token  !! Invalid token");
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            logger.info("Invalid Header Value");
        }
        if (username!= null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {
                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.info("Validation fails !!");
            }
        }
        filterChain.doFilter(request, response);
    }
        }

