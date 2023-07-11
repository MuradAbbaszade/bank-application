package com.company.filter;


import org.springframework.security.authentication.BadCredentialsException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

public class UsernameCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if(authorizationHeader!=null){
            String encodedString = authorizationHeader.substring(6);
            byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
            String usernamePassword = new String(decodedBytes);
            String username = usernamePassword.split(":")[0];
            if (username.toLowerCase().equals("test")) {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
