package com.example.mutbooks.domain.member.dto;

import com.example.mutbooks.domain.member.entity.Member;
import com.example.mutbooks.global.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginFormDto {
    private String username;

    private String password;

    private String nickname;

    private String email;

    private Role role;

    /* DTO -> Entity */
    public Member toEntity() {
        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .role(role.USER)
                .build();
        return member;
    }
}
