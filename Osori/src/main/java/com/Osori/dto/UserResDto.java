package com.Osori.dto;

import com.Osori.domain.entity.User;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResDto {
    private Long id;
    private String nickname;
    private TokenDto token;

    public static UserResDto of(User user, TokenDto token){
        return UserResDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .token(token)
                .build();
    }
}
