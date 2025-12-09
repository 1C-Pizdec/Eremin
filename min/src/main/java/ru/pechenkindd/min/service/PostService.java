package ru.pechenkindd.min.service;

import ru.pechenkindd.min.model.Post;
import ru.pechenkindd.min.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository repo;

    public PostService(PostRepository repo) {
        this.repo = repo;
    }

    public List<Post> findAll() {
        return repo.findAll();
    }

    public Post save(Post post) {
        if (post.getCreatedAt() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }
        return repo.save(post);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Post> findBetween(LocalDateTime start, LocalDateTime end) {
        return repo.findAllByCreatedAtBetween(start, end);
    }

    public Post getPostByID(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Постa c id %s нет", id)));
    }
}
