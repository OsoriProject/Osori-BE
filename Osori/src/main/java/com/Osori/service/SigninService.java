package com.Osori.service;

import com.Osori.domain.entity.User;
import com.Osori.domain.repository.UserRepository;
import com.Osori.dto.SigninReqDto;
import com.Osori.dto.TokenDto;
import com.Osori.dto.UserResDto;
import com.Osori.exception.CustomException;
import com.Osori.exception.ErrorCode;
import com.Osori.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SigninService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    private UserResDto checkUser(SigninReqDto signinReqDto){
        User user = userRepository.findByEmail(signinReqDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_USER));

        if(!signinReqDto.getPassword().equals(user.getPassword())){
            throw new CustomException(ErrorCode.MISMATCH_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),"",null);
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        return UserResDto.of(user, tokenDto);
    }

    public UserResDto Login(SigninReqDto signinReqDto){
        return checkUser(signinReqDto);
    }
}
