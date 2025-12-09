package ru.pechenkindd.min.repository;

import ru.pechenkindd.min.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}

