package com.company.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

public class LoggingFilter implements Filter {

    Logger LOG = Logger.getLogger(LoggingFilter.class.getName());
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null){
            LOG.info(authentication.getName()+" authenticated");
        }
    }
}
