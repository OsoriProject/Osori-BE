package com.Osori.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {
    public String email;
    public String password;
    public String nickname;
}
