package com.example.mutbooks.domain.member.controller;

import com.example.mutbooks.domain.member.dto.SignUpFormDto;
import com.example.mutbooks.domain.member.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        return "member/member_login";
    }
    /* 회원가입 */
    @GetMapping("/member/join")
    public String showSignUPFormDto(Model model) {
        model.addAttribute("signUpFormDto", new SignUpFormDto());
        return "member/member_join";
    }

    @PostMapping("/member/join")
    public String doSignUp(@Validated @ModelAttribute SignUpFormDto signUpFormDto,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/member_join";
        }

        try {
            signUpService.join(signUpFormDto);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("duplicatedUsername", "이미 등록된 아이디 입니다");
            return "member/member_join";
        } catch (Exception e) {
            return "member/member_join";
        }

        return "redirect:login";
    }
}
