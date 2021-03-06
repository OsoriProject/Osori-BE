package com.Osori.service;

import com.Osori.domain.entity.User;
import com.Osori.domain.repository.UserRepository;
import com.Osori.dto.SignupReqDto;
import com.Osori.exception.CustomException;
import com.Osori.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;

    public void Register(SignupReqDto signupReqDto){
        if(userRepository.existsByEmail(signupReqDto.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_EXIST_EMAIL);
        }

        User user = signupReqDto.toEntity();
        userRepository.save(user);
    }
}
