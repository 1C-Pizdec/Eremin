package ru.pechenkindd.min.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String content;

    private LocalDateTime createdAt;

    public Post() {}

    public Post(String title, String content, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    // getters

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    // setters

    public void setCreatedAt(LocalDateTime time) {
        this.createdAt = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
