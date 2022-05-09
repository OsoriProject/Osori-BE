package com.Osori.service;

import com.Osori.domain.entity.Playlist;
import com.Osori.domain.entity.User;
import com.Osori.domain.repository.PlaylistRepository;
import com.Osori.domain.repository.UserRepository;
import com.Osori.dto.PlaylistResDto;
import com.Osori.exception.CustomException;
import com.Osori.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    public PlaylistResDto getPlaylist(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_USER));

        List<Playlist> playlists = playlistRepository.findByUser(user);

        return PlaylistResDto.of(playlists);
    }
}
