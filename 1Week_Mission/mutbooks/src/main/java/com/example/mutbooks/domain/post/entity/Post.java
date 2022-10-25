package com.example.mutbooks.domain.post.entity;

import com.example.mutbooks.domain.member.entity.Member;
import com.example.mutbooks.global.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String subject;

    /* 마크다운 */
    /* 대용량 데이터를 저장할 때 사용*/
    @Lob
    private String content;

    /* 토스트에디터 렌더링 결과 */
    @Lob
    private String contentHtml;

    /* 한 명의 유저가 여러 게시글을 작성할 수 있으므로 @ManyToOne을 사용 */
    /* 게시글을 작성할 때 누가 작성했는지 알아야 하기 때문에 Member 테이블과 조인 */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberId")
    private Member member;

    /*  JPA에서 udpate를 진행할 때, 영속성 컨텍스트에 있는 값과 비교를 해서 변경된 값이 있다.
        그 변경된 값만 update 시켜주는데, 이를 변경감지라 하여 '더치체킹'이라 함.
        Entity 객체의 값만 변경시켜주면 더티체킹이 동작.
        Update 쿼리문을 날릴 필요가 없다.
     * */
    public void modify(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}
