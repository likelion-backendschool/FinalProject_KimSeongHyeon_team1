## Title: [3Week] 김성현

### 미션 요구사항 분석 & 체크리스트

---
## 📝미션 요약

### **필수과제**

- 관리자 회원, 관리자페이지, 정산데이터 생성, 건별정산처리, 전체정산처리

### **추가과제**

- 정산데이터를 배치로 생성
    - 스프링 내장 스케쥴러를 이용해서 배치가 매달 15일 새벽 4시에 실행되도록
    - `@EnableScheduling` 사용
    - Quartz 사용금지, 스프링의 기본적인 스케쥴링 기능을 이용해주세요.
- 출금신청기능(사용자기능)
- 출금처리기능(관리자기능)

## ✅체크리스트

Spring boot + gradle + data JPA + Spring Security + mysql + ajax + Thymeleaf 

---
## commit message
"feat:  게시판 CRUD  #1

- 더티체킹, ajax를 이용한 crud 구현
- 글 리스팅, 작성, 삭제, 수정까지 작성완료
- 리스팅을 제외한 기능에서 이슈 해결중"

### ISSUE message
### 목적
> Spring Security로 회원가입 구현

### 작업 상세사항
- [x] 관리자 페이지
- [ ] 회원자격 변경 기능 구현
- [x] 정산
- [ ] 출금

2주차
### 관리자 페이지 및 회원자격 변경 기능
- [x] 기획(페이지, 기능)
- [x] 관리자 페이지 구현
- [ ] authLevel에 따른 회원자격 변경 기능 구현[사용자-고객, 작가]/[관리자]

### 정산
- [x] 정산데이터 저장을 위한 DB생성
- [x] 사용자(작가) 건별, 전체 정산결과 조회 기능

### 출금
- [ ] 예치금 DB
- [ ] 출금 신청 기능(정산페이지에서)
- [ ] 출금 신청양식 폼 구현
- [ ] 관리자페이지에서 출금신청 목록 조회

---
## **[접근 방법]**

**과제 목표**
1. 코드와 git의 통일성 지키기.
2. 계획한 필수 기능 구현.
3. 기능 구현 후 이슈 단위로 개선할 점 피드백하기. + (참고, 설명, 리펙터링)    

도서를 등록하는 사람을 작가라고 했을 때, 자신의 등록도서 매출내역을 볼 수 있도록 하는 기능이 필요하다고 생각했다.
관리자는 모든 작가들의 출금을 관리하는 역할을 한다.

<img width="1398" alt="image" src="https://user-images.githubusercontent.com/53210680/199414643-3ce7ba81-7df5-41a2-bc6d-f2909d550f9d.png">

---
### 이슈정리

1. 정산DB는 만들었지만 이를 관리, 조회하는 기능은 주어진 시간 내 구현이 힘들었다. 정산을 하기위해 Order 엔티티의 내용이 필요할 것이라 생각했고 이는 작가 개개인의 매출확인 기능을 구현하기로 방향을 틀었다. 요구사항에는 정산 조회 api가 post 요청으로 되어있었는데 단순히 DB정보를 가져와서 매출내역을 보여주는거라면 get요청이 나을 것이라 생각했다.
->하지만 정산처리라면 post가 맞다.
2. 정산DB생성 시 이전 수업의 ERD를 참고했다. 이번 final project에서 쓰지 않는 컬럼은 제외했다. 참고한 git에서 Order과 Product가 RebateOrderItem과 다대일 관계로 구성되어 있었다. 엔티티관계를 맺는게 익숙지 않은데 좋은 참고가 되었다.
3. 정산페이지는 url에 사용자(관리자)계정정보, username이 포함되게 했고, 정산DB에 담긴 내용을 보여주도록 했다. thymeleaf에서 username을 인식하도록 하기 위해서 principal을 통해 현재 로그인한 계정정보를 url에 담아 redirect하도록 했다.
4.  No enum constant com.example.mutbooks.domain.pay.entity.enumulation.PayStatus.AFTER
->paystatus에서 결제완료는 complete로 작성했었는데 after로 db에 저장해서 다음과 같은 오류가 떴다.

미해결 이슈

1. 권한에 따라 작가는 네비바에 정산조회가 뜨고, 그 외 사용자는 뜨지 않도록 해야하는데 그 작업을 못했다.
2. 정산내역을 자신아 등록한 도서를 기준으로 보여줘야하는데 도서주문자를 기준으로 보여주고 있다.
-> order 엔티티의 컬럼인 username은 주문자의 계정이다. 이를 등록도서 기준으로 보여주려면 order테이블이 아니라 product와 연관테이블인 정산 테이블을 보여줬어야했다.


---
### 느낀 점과 아쉬운 점.

이번 3주차 과제는 멋북스의 정산과 출금 그리고 회원권한 변경이다. 회원권한 변경은 기존의 authLevel을 signIn할 때 달리 주는 식으로 해결하면 될 것이라 생각했다.
앞의 기능보다 정산에 대해 고민거리가 더 많이 생겼었다. 정산이 과연 어떻게 이뤄지는지, 누구한테 필요한 기능인지 생각했다. 이런 고민을 푸는데 요구사항 명세서가 힌트가 되었다.
등록도서의 판매수익을 작가와 멋북스 플랫폼 간 5대5의 비율로 가져간다는 점에서 실무에서 수익배분이 어떻게 이뤄질 것인지 알 수 있었다. 
수익과 3주차 과제를 하면서 컨디션이 좋지 않아 계획했던 것보다 많은 부분을 구현하지 못했다. 이번 결과물은 정산이나 구매 이력 조회지, 처리가 아니였다. 정산부분에서 건별, 전체처리를 위한 폼을 만들고
컨트롤러와 서비스단에 작성했어야했다. 리펙토링 기간동안 1. 정산처리를 위한 폼 작성 2. 작성완료를 눌렀을 경우 정산db로 넘어갈 수 있도록 하는 작업을 해야겠다.
