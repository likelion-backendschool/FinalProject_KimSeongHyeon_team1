package com.example.mutbooks.domain.post.entity;

import com.example.mutbooks.domain.member.entity.Member;
import com.example.mutbooks.global.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String subject;

    @Column(nullable = false, length = 100)
    private String content;

    public void modify(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}
