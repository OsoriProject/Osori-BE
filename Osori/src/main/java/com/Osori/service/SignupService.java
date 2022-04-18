package com.Osori.service;

import com.Osori.domain.entity.User;
import com.Osori.domain.repository.UserRepository;
import com.Osori.dto.UserDto;
import com.Osori.exception.CustomException;
import com.Osori.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;

    public void Register(UserDto userDto){
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_EXIST_EMAIL);
        }

        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .build();
        userRepository.save(user);
    }
}
