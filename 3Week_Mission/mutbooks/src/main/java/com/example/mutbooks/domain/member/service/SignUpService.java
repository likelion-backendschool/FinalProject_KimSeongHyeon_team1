package com.example.mutbooks.domain.member.service;

import com.example.mutbooks.domain.member.dto.SignUpFormDto;
import com.example.mutbooks.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class SignUpService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Long join(SignUpFormDto dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));

        return memberRepository.save(dto.toEntity()).getId();
    }

}
