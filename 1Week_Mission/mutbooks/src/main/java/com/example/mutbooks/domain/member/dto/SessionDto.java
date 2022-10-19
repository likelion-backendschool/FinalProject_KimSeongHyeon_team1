package com.example.mutbooks.domain.member.dto;

import com.example.mutbooks.domain.member.entity.Member;
import com.example.mutbooks.global.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionDto implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Role role;

    /* 빈 생성자 */
    /* Entity -> Dto */
    public SessionDto(){

    }
    public SessionDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.role = member.getRole();
    }
    /* DTO -> Entity */
    public Member toEntity() {
        Member member = Member.builder()
                .id(id)
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .role(role.USER)
                .build();
        return member;
    }
}
