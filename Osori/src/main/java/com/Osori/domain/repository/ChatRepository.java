package com.Osori.domain.repository;

import com.Osori.domain.entity.Chat;
import com.Osori.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByUser(User user);
}
