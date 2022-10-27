package com.example.mutbooks.domain.post.repository;

import com.example.mutbooks.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
