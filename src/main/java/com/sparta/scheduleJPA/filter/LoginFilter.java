package com.sparta.scheduleJPA.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/", "/users/signup", "/users/login", "/users/search", "/users/search/*"};


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // whitelist에 포함되지 않으면 조건문 실행
        if(!isWhiteList(requestURI)){
            HttpSession session = httpRequest.getSession(false);

            if(session==null || session.getAttribute("userKey") == null){
                throw new RuntimeException("로그인 해주세요.");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
