# 📆 Sparta_Schedule_JPA
스파르타코딩 내일배움캠프 - 일정 관리 프로젝트 with JPA

## 📌 프로젝트 소개
Java - Spring, Spring JPA 프레임워크를 사용하여 일정을 관리하는 앱의 서버 환경을 구현합니다.

## 🛠 개발 환경
- `Java`
- `Oracle OpenJDK 17.0.5`
- 프레임워크 : `Spring Boot 3.4.5`, `Spring-data-jpa`
- 데이터베이스 : `MySQL`
- IDE : `IntelliJ IDEA`

## 🔍 주요 기능
유저 CRUD
- 유저 등록
  - 이름, 이메일, 비밀번호
- 유저 로그인
  - 이메일, 비밀번호
- 유저 조회
  - id 또는 이름 기반 유저 조회
- 유저 정보 수정
  - 이름, 이메일, 비밀번호 수정 가능
  - 로그인 상태에서 가능
- 유저 삭제
  - 로그인 상태에서 가능

일정 CRUD
- 일정 생성
  - 일정 제목, 일정 내용
  - id, 작성일, 수정일 자동 생성
  - 로그인 상태에서 가능
- 일정 조회
  - 유저의 전체 일정 목록 조회
  - id 기반 단건 일정 조회
  - 로그인 상태에서 가능
- 일정 수정
  - 일정 제목, 일정 내용
  - 단건 일정 수정 가능
  - 로그인 상태에서 가능
- 일정 삭제
  - 단건 일정 삭제
  - 로그인 상태에서 가능

--------------------
## 📋 API 명세서
아래는 주요 기능을 요약하여 정리한 표입니다. 

user
| 기능            | Method       | URL                   | Request        | Request 샘플                                                                  | reponse       | 상태코드      |
|-----------------|--------------|-----------------------|----------------|-------------------------------------------------------------------------------|---------------|---------------|
| 유저 등록        | POST         | /users/signup         | 요청 body<br>1.이름 : String<br>2.이메일 : String<br>3.비밀번호 : String      | {<br>"name":"김철수"<br>"email":"eee@gmail.com"<br>"password":"pass1234"<br>} | 등록 정보      | 201 : Created |
| 유저 로그인      | POST         | /users/login          | 요청 body<br>1.이메일 : String<br>2.비밀번호 : String      | {<br>"email":"eee@gmail.com"<br>"password":"pass1234"<br>} | 등록 정보      | 200 : OK      |
| 유저 정보 조회   | GET          | /users/search/{id}    | 요청 param<br>1.유저 id : Long     | /users/search/1 | 단건 응답 정보 | 200 : OK      |
| 유저 정보 조회   | GET          | /users/search         | 요청 param<br>1.이름 : String     | /users/search?name=김철수 | 다건 응답 정보 | 200 : OK      |
| 유저 정보 수정   | PATCH        | /users/edit           | 요청 body<br>1.이름 : String<br>2.이메일 : String<br>3.이전 비밀번호 : String<br>4.새로운 비밀번호 : String      | {<br>"name":"김짱구"<br>"email":"qqq@gmail.com"<br>"oldPassword":"pass1234"<br>"newPassword":"pass4567"<br>} | 단건 응답 정보 | 200 : OK      |
| 유저 정보 삭제   | DELETE       | /users/delete         | 요청 body<br>1.비밀번호 : String      | {<br>"password":"pass1234"<br>} | -             | 200 : OK      |

schedule
| 기능            | Method       | URL                   | Request                                                                                  | Request 샘플                                                           | reponse       | 상태코드      |
|-----------------|--------------|-----------------------|------------------------------------------------------------------------------------------|------------------------------------------------------------------------|---------------|---------------|
| 일정 등록        | POST         | /schedules/create     | 요청 body<br>1.제목 : String<br>2.할일 : String                                           |{<br>"title":"일정 제목"<br>"content":"일정 내용"<br>}                    | 등록 정보      | 201 : Created |
| 일정 전체 조회   | GET          | /schedules            | -                                                                                        |                                                                         | 다건 응답 정보 | 200 : OK      |
| 일정 단건 조회   | GET          | /schedules/{id}       | 요청 param<br>1.일정 id : Long                                                            |/schedules/1                                                             | 단건 응답 정보 | 200 : OK      |
| 일정 수정        | PATCH        | /schedules/{id}       | 요청 param<br>1.일정 id : Long<br><br>요청 body<br>1.제목 : String<br>2.할일 : String      |/schedules/1<br><br>{<br>"title":"일정 제목"<br>"content":"일정 내용"<br>}| 단건 응답 정보 | 200 : OK      |
| 일정 삭제        | DELETE       | /schedules/{id}       | 요청 param<br>1.일정 id : Long                                                            |/schedules/1                                                            | -             | 200 : OK      |


**자세한 요청/응답 스펙과 예시는 [Postman 문서](https://documenter.getpostman.com/view/30877327/2sB2qcD1XP)에서 확인 가능합니다.**

--------------------
## 🧾 ERD
사용자가 등록한 일정 정보를 저장하고 관리하기 위한 `schedule`, `user` 테이블의 구조입니다.

![image](https://github.com/user-attachments/assets/f560008c-4e79-45b0-92bd-f79cc22731fc)

