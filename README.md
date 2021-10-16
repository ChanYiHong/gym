# 요가 학원 수업 예약 사이트

## 기술 스택  
JAVA, Spring Framework, JPA, Thymeleaf,  </br>
Spring Data JPA, Querydsl, HTML5, CSS, Javascript, </br>
Ajax, GitHub

### 개발 기간 : 2021.06 ~ 2021.07

여자친구가 다니는 요가 학원에서 시간표를 보고 매주 불편하게 직접 문자나 전화로 예약하는 모습을 보고 편하게 예약하는 사이트를 만들면 어떨까 해서 만들게 된 사이트입니다. </br>
회원가입 후 회원은 멤버쉽 가입을 해야 합니다. 멤버쉽에는 주 5회 6개월, 주 2회 1년, 등 다양한 멤버쉽이 존재합니다. </br>
가입한 멤버쉽에 따라 해당 회원은 수업 예약을 진행할 수 있습니다. 시간표를 보고 원하는 수업을 클릭해서 예약을 진행합니다. </br>
멤버쉽에 정해진 일 수를 넘어가거나 멤버쉽 기한이 다되었다면 예약이 불가능합니다. </br>
예약은 회원정보 화면에서 취소할 수 있습니다. </br>
어떤 수업이 진행되는지 알고 싶다면 수업 목록에서 정보를 확인 할 수 있습니다. </br>
또한 자유게시판 기능을 만들었습니다. </br>
이전과는 다르게 대댓글 기능을 추가해 다른 사용자가 작성한 댓글 밑에 댓글을 추가로 달 수 있게 제작했습니다. </br>

- Javascript와 AJAX 를 활용해 예약 화면 시간표 작성
- Intercepter 와 ArgumentResolver 와 Session 활용해 로그인 구현
- 회원 가입, 자유게시판 게시글 작성 시 Validation 기능 추가
- Message를 통한 국제화 기능 사용
- UI는 BootStrap을 참고
- Ajax 기술을 활용해 JSON API 구현 (댓글, 대댓글)
- Controller, Service, Repository, DTO, Domain 패키지로 나누어 유지보수가 쉽게 설계

### 테이블 설계

<img width="889" alt="스크린샷 2021-10-16 오후 11 56 33" src="https://user-images.githubusercontent.com/33217033/137592183-bfd7ec33-f92d-4871-90e9-0efe10b7cae3.png">
