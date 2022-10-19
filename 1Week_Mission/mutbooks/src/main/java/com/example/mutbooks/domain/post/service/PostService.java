package com.example.mutbooks.domain.post.service;

import com.example.mutbooks.domain.member.entity.Member;
import com.example.mutbooks.domain.post.dto.PostSaveRequestDto;
import com.example.mutbooks.domain.post.entity.Post;
import com.example.mutbooks.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    /* 글 전체 보기 로직 */
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    /* 글 작성 로직 */
    @Transactional
    public Long save(PostSaveRequestDto postSaveRequestDto, Member member) {
        postSaveRequestDto.setMember(member);
        return postRepository.save(postSaveRequestDto.toEntity()).getId();
    }

    /* 글 상세보기 로직 */
    @Transactional(readOnly = true)
    public Post detail(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
    }

    /* 글 삭제 로직 */
    @Transactional
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    /* 글 수정 로직 */
    @Transactional
    public Long modify(Long id, PostSaveRequestDto postSaveRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
        post.modify(postSaveRequestDto.getSubject(), postSaveRequestDto.getContent());
        return id;
    }
}
