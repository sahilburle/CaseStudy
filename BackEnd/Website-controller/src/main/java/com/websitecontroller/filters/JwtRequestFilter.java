package com.websitecontroller.filters;

import com.websitecontroller.security.service.DetailsUserService;
import com.websitecontroller.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private DetailsUserService detailsUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //getting authorization header from  request headers
        final String authorizationheader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationheader != null && authorizationheader.startsWith("Bearer ")) {
            jwt = authorizationheader.substring(7); //fetching jwt
            System.out.println("Getting Jwt From Header: " + jwt);
            username = jwtUtil.extractUsername(jwt); // extracting username
            System.out.println("Extracted Username from JWT: " + username);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("Fetching data from UserName: ");
            UserDetails userDetails = detailsUserService.loadUserByUsername(username); //loading user by username

            if (jwtUtil.validateToken(jwt, userDetails)) {
                System.out.println("Validating JWT " + jwt);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken
                                (userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
