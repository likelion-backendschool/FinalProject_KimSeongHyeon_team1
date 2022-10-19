package com.example.mutbooks.domain.post.dto;

import com.example.mutbooks.domain.member.entity.Member;
import com.example.mutbooks.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostSaveRequestDto {
    private String subject;
    private String content;
    private String contentHtml;
    private Member member;

    public Post toEntity() {
        return Post.builder()
                .subject(subject)
                .content(content)
                .contentHtml(contentHtml)
                .member(member)
                .build();
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
