package com.example.mutbooks.domain.post.controller;

import com.example.mutbooks.domain.member.annotation.LoginUser;
import com.example.mutbooks.domain.member.dto.SessionDto;
import com.example.mutbooks.domain.post.dto.PostSaveRequestDto;
import com.example.mutbooks.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    /* 회원정보수정에는 SessionDto라 했는데 변수명 일관성이 있으면 좋을거 같다.*/
    /* 글 작성하기 */
    @PostMapping("/post")
    public Long doSave(@RequestBody PostSaveRequestDto postSaveRequestDto, @LoginUser SessionDto sessionDto) {
        /* 인자값으로 dto가 와도 괜찮을까 */
        return postService.save(postSaveRequestDto, sessionDto.toEntity());
    }

    /* 글 삭제하기 */
    @DeleteMapping("/post/{id}/delete")
    public Long doDeleteById(@PathVariable Long id) {
        postService.deleteById(id);
        return id;
    }

    /* 글 수정하기 */
    @PutMapping("/post/{id}/modify")
    public Long doModify(@PathVariable Long id, @RequestBody PostSaveRequestDto postSaveRequestDto) {
        return postService.modify(id, postSaveRequestDto);
    }
}
