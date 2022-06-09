package com.Osori.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    private SecurityUtil() { }

    public static String getCurrentUserEmail() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw  new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }

        return authentication.getName();
    }
}
