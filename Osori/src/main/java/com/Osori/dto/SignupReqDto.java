package com.Osori.dto;

import com.Osori.domain.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class SignupReqDto {
    public String email;
    public String password;
    public String nickname;

    public User toEntity(){
        return User.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .nickname(this.getNickname())
                .build();
    }
}
