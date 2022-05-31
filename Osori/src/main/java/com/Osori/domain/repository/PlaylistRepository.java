package com.Osori.domain.repository;

import com.Osori.domain.entity.Playlist;
import com.Osori.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByUser(User user);
    Optional<Playlist> findByIdAndUser(Long id, User user);
}
