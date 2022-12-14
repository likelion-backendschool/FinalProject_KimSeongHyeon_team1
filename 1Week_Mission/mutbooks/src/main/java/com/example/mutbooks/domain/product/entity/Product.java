package com.example.mutbooks.domain.product.entity;

import com.example.mutbooks.domain.member.entity.Member;
import com.example.mutbooks.domain.post.entity.PostKeyword;
import com.example.mutbooks.global.BaseEntity;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private int price;

    @Column(nullable = true)
    private String imgUrl;  //url

    @Lob
    @Column(nullable = true)
    private String description;

    //멤버 테이블 연관관계 맺기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private Member member;

    @OneToMany(mappedBy = "product")     // 음식점 url 식별번호로 해당 음식점 메뉴들을 조회할 수 있다.
    private List<PostKeyword> postKeywords = new ArrayList<>();
}

