package com.Osori.jwt;

import com.Osori.exception.CustomException;
import com.Osori.exception.ErrorCode;
import com.Osori.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        writer.println("{ " + "\"timestamp\" : \"" + LocalDateTime.now()
                + "\", \"status\" : \"" +  ErrorCode.NOT_EXIST_TOKEN.getStatus().value()
                + "\", \"error\" : \"" + ErrorCode.NOT_EXIST_TOKEN.getStatus().name()
                + "\", \"code\" : \"" + ErrorCode.NOT_EXIST_TOKEN.name()
                + "\", \"message\" : \"" + ErrorCode.NOT_EXIST_TOKEN.getMessage()
                + "\"}");
    }
}
