package com.Osori.service;

import com.Osori.domain.entity.User;
import com.Osori.domain.repository.UserRepository;
import com.Osori.dto.SigninReqDto;
import com.Osori.dto.UserResDto;
import com.Osori.exception.CustomException;
import com.Osori.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SigninService {
    private final UserRepository userRepository;

    private UserResDto checkUser(SigninReqDto signinReqDto){
        User user = userRepository.findByEmail(signinReqDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_USER));

        if(!signinReqDto.getPassword().equals(user.getPassword())){
            throw new CustomException(ErrorCode.MISMATCH_PASSWORD);
        }

        return UserResDto.of(user);
    }

    public UserResDto Login(SigninReqDto signinReqDto){
        return checkUser(signinReqDto);
    }
}
