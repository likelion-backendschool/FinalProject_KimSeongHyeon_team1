package com.example.mutbooks.domain.member.dto;

import com.example.mutbooks.domain.member.entity.Member;
import com.example.mutbooks.global.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpFormDto {
    @NotEmpty(message = "아이디는 필수항목입니다.")
    private String username;
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;
    private String nickname;
    @NotEmpty(message = "이메일은 필수항목입니다.")
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
