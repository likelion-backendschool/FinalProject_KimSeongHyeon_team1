package com.example.mutbooks.domain.member.controller;

import com.example.mutbooks.domain.member.annotation.LoginUser;
import com.example.mutbooks.domain.member.dto.SessionDto;
import com.example.mutbooks.domain.member.dto.SignUpFormDto;
import com.example.mutbooks.domain.member.service.ModifyService;
import com.example.mutbooks.domain.member.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final SignUpService signUpService;
    private final ModifyService modifyService;

    /* 로그인 후 메인화면*/
    @GetMapping("/home")
    public String showHome(Model model) {
        return "home";
    }

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
                           BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {

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

        /* uuid로 쿠키값을 준 뒤 세션을 구분하기 위해 사용*/
        UUID uuid = UUID.randomUUID();
        Cookie cookie = new Cookie("userLogin", uuid.toString());
        response.addCookie(cookie);

        HttpSession session = request.getSession(true);
        session.setAttribute(uuid.toString(), signUpFormDto.getUsername());


        return "redirect:login";
    }
    /* 회원정보 수정*/
    @GetMapping("/member/modify")
    public String modify(@LoginUser SessionDto sessionDto, Model model) {
        if (sessionDto != null) {
            model.addAttribute("sessionDto", sessionDto);
        }
        return "member/member_modify";
    }

//    post 방식 말고 put 방식으로 수정을 해보자.
//    @PostMapping("/member/modify")
//    public String modify(@ModelAttribute SessionDto sessionDto) {
//
//        modifyService.modify(sessionDto);
//        return "redirect:home";
//    }


    /* 로그아웃 */
    /* auth 값을 가져와서 만약 null이 아니라면 logout과 함께 Login 페이지로 리다이렉트*/
    @GetMapping("/member/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:login";
    }

}
/*  1. 페이지별로 로그인 상태여야만 들어갈 수 있게 권한 설정하기.
*   2. 회원정보 수정 후 뒤로가기 했을 때, 수정 전이 보이지 않게 하기.( 아직 테스트 x)
* */