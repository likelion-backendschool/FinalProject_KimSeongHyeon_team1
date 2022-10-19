package com.example.mutbooks.domain.post.controller;

import com.example.mutbooks.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    /* 글 전체 목록 조회 */
    @GetMapping("/post/list")
    public String showList(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "post/post_list";
    }

    /* 글 상세보기 */
    @GetMapping("/post/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.detail(id));
        return "post/post_detail";
    }

    /* 글 작성하기 */
    @GetMapping("/post/save")
    public String showSave() {
        return "post/post_save";
    }

    /* 글 수정하기 */
    @GetMapping("/post/{id}/modify")
    public String showModify(@PathVariable Long id, Model model) {
        model.addAttribute("board", postService.detail(id));
        return "post/post_modify";
    }
}
