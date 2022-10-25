package com.example.mutbooks.domain.member.controller;

import com.example.mutbooks.domain.member.dto.SessionDto;
import com.example.mutbooks.domain.member.service.ModifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* 변경된 세션을 바로 등록하기 위한 클래스 */
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberApiController {

    private final ModifyService modifyService;

    private final AuthenticationManager authenticationManager;

    @PutMapping("/member")
    public ResponseEntity<String> modify(@RequestBody SessionDto dto) {
        modifyService.modify(dto);

        /* 변경된 세션 등록 */
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
