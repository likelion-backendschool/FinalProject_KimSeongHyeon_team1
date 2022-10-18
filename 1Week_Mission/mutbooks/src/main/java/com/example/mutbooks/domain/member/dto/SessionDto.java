package com.example.mutbooks.domain.member.dto;

import com.example.mutbooks.domain.member.entity.Member;
import com.example.mutbooks.global.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionDto implements Serializable {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Role role;

    /* Entity -> Dto */
    public SessionDto(Member member) {
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.role = member.getRole();
    }
}
