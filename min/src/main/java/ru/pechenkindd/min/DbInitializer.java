package ru.pechenkindd.min;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.pechenkindd.min.model.Post;
import ru.pechenkindd.min.repository.PostRepository;

import java.time.LocalDateTime;

@Component
public class DbInitializer implements CommandLineRunner {

    private final PostRepository repo;

    public DbInitializer(PostRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.save(new Post("First post", "Hello world", LocalDateTime.now().minusDays(2)));
        repo.save(new Post("Second post", "Today news", LocalDateTime.now()));
    }
}