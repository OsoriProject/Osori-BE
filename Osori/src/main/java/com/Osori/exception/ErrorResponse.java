package com.Osori.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    public LocalDateTime timestamp;
    public int status;
    public String error;
    public String code;
    public String message;

    public static ErrorResponse of(ErrorCode errorcode){
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(errorcode.getStatus().value())
                .error(errorcode.getStatus().name())
                .code(errorcode.name())
                .message(errorcode.getMessage())
                .build();
    }
}
