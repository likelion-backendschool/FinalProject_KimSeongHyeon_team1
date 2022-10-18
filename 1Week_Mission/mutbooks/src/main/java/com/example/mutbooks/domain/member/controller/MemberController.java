package com.example.mutbooks.domain.member.controller;

import com.example.mutbooks.domain.member.dto.SignUpFormDto;
import com.example.mutbooks.domain.member.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final SignUpService signUpService;

    /* 로그인 */
    @GetMapping("/member/login")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/user-login";
    }
    /* 회원가입 */
    @GetMapping("/member/join")
    public String showSignUPFormDto() { return "/member/member_join"; }

    @PostMapping("/member/join")
    public String doSignUp(SignUpFormDto signUpFormDto) {
        signUpService.join(signUpFormDto);

        return "redirect:/auth/login";
    }
}
