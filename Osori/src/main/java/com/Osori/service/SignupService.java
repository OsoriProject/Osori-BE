package com.Osori.service;

import com.Osori.domain.entity.User;
import com.Osori.domain.repository.UserRepository;
import com.Osori.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;

    public void Register(UserDto userDto){
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .build();
        userRepository.save(user);
    }
}
