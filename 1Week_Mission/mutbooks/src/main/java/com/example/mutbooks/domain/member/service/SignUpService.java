package com.example.mutbooks.domain.member.service;

import com.example.mutbooks.domain.member.dto.SessionDto;
import com.example.mutbooks.domain.member.entity.Member;
import com.example.mutbooks.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class SignUpService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final HttpSession session;

    /* username이 DB에 있는지 확인 */
    /* UserDetails는 springSecurity에 정의되어있는대로 변수명 사용*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + username));

        session.setAttribute("user", new SessionDto(member));

        /* 시큐리티 세션에 유저 정보 저장 */
        return new MemberDetails(member);
    }
}
