package com.Osori.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SigninReqDto {
    private String email;
    private String password;
}
