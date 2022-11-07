package com.example.mutbooks.domain.member.service;

import com.example.mutbooks.domain.member.dto.SessionDto;
import com.example.mutbooks.domain.member.dto.SignUpFormDto;
import com.example.mutbooks.domain.member.entity.Member;
import com.example.mutbooks.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ModifyService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder encoder;

    /* 회원수정 */
    @Transactional
    public void modify(SessionDto dto) {
        Member member = memberRepository.findById(dto.toEntity().getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        String encPassword = encoder.encode(dto.getPassword());
        member.modify(dto.getNickname(), encPassword);
    }
}
