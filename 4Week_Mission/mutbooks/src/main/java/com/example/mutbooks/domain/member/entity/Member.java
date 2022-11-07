package com.example.mutbooks.domain.member.entity;

import com.example.mutbooks.global.BaseEntity;
import com.example.mutbooks.global.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "members")
/* 쿼리문은 나가지만, 테이블이 생성이 안될 경우 예약어일 가능성이 있음. */
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username; // 아이디

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /* 회원정보 수정 (필명, 비밀번호 변경 가능)*/
    public void modify(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
