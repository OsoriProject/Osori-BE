package com.Osori.domain.repository;

import com.Osori.domain.entity.Chat;
import com.Osori.domain.entity.Playlist;
import com.Osori.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByUser(User user);
    List<Chat> findAllByPlaylist(Playlist playlist);
}
