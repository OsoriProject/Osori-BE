package com.Osori.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."), // 400
    ALREADY_EXIST_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일 입니다."),
    NOT_EXIST_USER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자 입니다."),
    MISMATCH_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 틀립니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드 입니다."), //405
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류 입니다."); // 500

    private final HttpStatus status;
    private final String message;
}
