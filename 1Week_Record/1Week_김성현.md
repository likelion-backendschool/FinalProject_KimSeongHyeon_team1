## Title: [1Week] 김성현

### 미션 요구사항 분석 & 체크리스트

---
## 📝1주차 미션 요약

### **필수과제**

- 회원가입, 회원정보수정, 로그인, 로그아웃, 아이디찾기
- 글 작성, 글 수정, 글 리스트, 글 삭제

### **추가과제**

- 비번찾기
- 상품 등록
- 상품 수정
- 상품 리스트
- 상품 상세페이지

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
- [x] 회원가입 기능
- [x] 로그인 기능
- [x] 로그아웃 기능
- [x] favicon 추가
- [x] 정적 리소스 인증해제

1주차
### 회원가입 기능
- [x] 회원가입 기능()
https://dev-coco.tistory.com/m/120
### 로그인 기능
- [x] 로그인 기능

### 부가 기능
- [x] 회원정보 수정 기능
- [x] 로그아웃 기능
- [ ] 아이디 찾기 기능
- [ ] 축하메일 발송

### 게시글 CRUD 기능
- [x] 글 리스트
- [x] 글 작성
- [x] 글 삭제
- [x] 글 수정
- [ ] 토스트 에디터 적용

### 추가작업
- [x] 파비콘 생성
- [x] nav bar 생성
- [x] 브랜드 로고 완성

---
## **[접근 방법]**

**과제 목표**
1. 코드와 git의 통일성 지키기.
2. 계획한 필수 기능 구현.
3. 기능 구현 후 이슈 단위로 개선할 점 피드백하기. + (참고, 설명, 리펙터링)    

---
### 이슈정리

1
dataJPA 구문오류로 테이블 생성이 안될 때.
https://devjaewoo.tistory.com/m/132
2.
org.apache.http.client.httpclient이 임포트 되지 않는 경우
https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient/4.5
3.
template might not exist or might not be accessible by any of the configured Template Resolvers
https://jg-han.tistory.com/100
4.
JPA Auditing 사용과 BaseEntity 만들기
https://eocoding.tistory.com/m/60
5.
OAuth란
https://gdtbgl93.tistory.com/180
6.
Security handler
https://kimchanjung.github.io/programming/2020/07/02/spring-security-02/
7.
favicon 변경
https://favicon.io/favicon-converter/
8.
cannot deserialize from object value (no delegate- or property-based creator)
https://azurealstn.tistory.com/m/74
빈 생성자를 추가해주니 해결되었음.
9.
thymeleaf Refused to execute script from because its MIME type ('text/html') is not executable, and strict MIME type checking is enabled.
https://stackoverflow.com/questions/24726218/spring-security-refused-to-execute-script-from
스프링 시큐리티에서 예외처리해주기.
10.
Tracking AJAX error on readystate 0, status 0 and statusText error
https://bitcoden.com/answers/tracking-ajax-error-on-readystate-0-status-0-and-statustext-error

---
### 느낀 점과 아쉬운 점.

해커톤을 진행할 때는 dataJPA에서 제공해주는 틀에 맞게 CRUD 기능을 구현했었다.
이번 종합프로젝트에서는 수업시간에서도 배웠던 ajax를 통해 회원정보 수정과 글쓰기를 구현했다.

이를 목표로 했던 이유는 많은 Spring boot 프로젝트에서 ajax와 apiController로 crud를 구현하고 있어, 다뤄보지 않으면 실무에서 어려움을 겪을 것이라 생각했다.

개인적으로 느꼈던 차이점은 생산성과 가독성이었다.
dataJPA로 Controller, Service, Repository 내에서 crud가 충분히 구현가능했다. 그래서 오류가 발생했을 때, 어느 곳에서 문제가 있는지 금방 찾을 수 있었다.
ajax로 구현하게 되면서 이슈가 발생했을 때 view단의 문제인지 controller단 문제인지 찾는데 어려웠다.
또한 js파일들을 따로 관리하고 싶었는데, 동작하지 않아 해당 html파일에 직접 넣는 식으로 시간이 많이 지체되었다. 결국 나한테 익숙하지 않은 생소함의 문제였다.

이번 과제로 Repository에서 find(), save()를 사용하지 않고 더티체킹을 이용해 crud를 구현해보았다.  해커톤 프로젝트 디벨롭과정에서 나왔던 패키지 개선점을 이번 프로젝트에 적용해보았고 소위, 클린코드, convention이 왜 필요한지 알게되었다.
그동안 프로젝트를 하면서 controller와 service의 구분이 모호했다. controller에 비즈니스 로직이 들어가면 다른 곳에 치우리라 다짐했었는데, 도메인별로 ApiController를 만들어보니 Get요청을 제외한 나머지 메서드들이 한 곳에 정리가 되었다. 조회요청과 아닌 api요청들이 나뉘면서 개인적으로 가독성이 좋아졌다고 느꼈다.

다만, 혼자서 프로젝트를 진행하면서 느낀 어려움은 개선이 필요했다.
예를 들어 회원가입, 로그인에 반드시 필요한 세션을 구현했는데 이게 Security에서 어떤 역할을 할 수 있을지 고민하고 헤맸었다. 프로젝트의 작동과정과 영속성과 같은 개념도 아직까지 생소하다. 적절히 배분해서 공부해야할 것 같다.