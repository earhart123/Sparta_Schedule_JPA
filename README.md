# 📆 Sparta_Schedule_JPA
스파르타코딩 내일배움캠프 - 일정 관리 프로젝트 Develop 버전

## 📌 프로젝트 소개
Java - Spring, Spring JPA 프레임워크를 사용하여 일정을 관리하는 앱의 서버 환경을 구현합니다.

## 🛠 개발 환경
- `Java`
- `Oracle OpenJDK 17.0.5`
- 프레임워크 : `Spring Boot 3.4.5`, `Spring-data-jpa`
- 데이터베이스 : `MySQL`
- IDE : `IntelliJ IDEA`

## 🔍 주요 기능
일정 CRUD
- 일정 생성
  - 할 일, 작성자, 비밀번호 저장
  - id, 작성일 자동 생성
- 일정 조회
  - 전체 목록 조회 (작성자명/수정일 필터링 가능)
  - id 기반 단건 일정 조회
- 일정 수정
  - 할일, 작성자명 단건 일정 수정 가능
  - 비밀번호 일치 여부 검증
- 일정 삭제
  - 단건 일정 삭제
  - 비밀번호 일치 여부 검증
 
유저 CRUD
- 유저 등록
- 유저 조회
- 유저 정보 수정
- 유저 삭제

--------------------
## 📋 API 명세서
아래는 주요 기능을 요약하여 정리한 표입니다. 

schedule
| 기능            | Method       | URL                   | Request                                                                                  | Request 샘플                                                           | reponse       | 상태코드      |
|-----------------|--------------|-----------------------|------------------------------------------------------------------------------------------|------------------------------------------------------------------------|---------------|---------------|
| 일정 등록        | POST         | /schedules            | 요청 body<br>1.제목 : String<br>2.할일 : String                                           |{<br>"title":"일정 제목"<br>"content":"일정 내용"<br>}                    | 등록 정보      | 201 : Created |
| 일정 전체 조회   | GET          | /schedules            | -                                                                                        |                                                                         | 다건 응답 정보 | 200 : OK      |
| 일정 목록 조회   | GET          | /schedules/{user_id}  | 요청 body<br>1.유저 정보 : User                                                           |{<br>"User : 유저 정보"<br>}                                              |다건 응답 정보 | 200 : OK      |
| 일정 단건 조회   | GET          | /schedules/{id}       | 요청 param<br>1.일정 id : Long                                                            |/schedules/1                                                             | 단건 응답 정보 | 200 : OK      |
| 일정 수정        | PATCH        | /schedules/{id}       | 요청 param<br>1.일정 id : Long<br><br>요청 body<br>1.제목 : String<br>2.할일 : String      |/schedules/1<br><br>{<br>"title":"일정 제목"<br>"content":"일정 내용"<br>}| 단건 응답 정보 | 200 : OK      |
| 일정 삭제        | DELETE       | /schedules/{id}       | 요청 param<br>1.일정 id : Long                                                            |/schedules/1                                                            | -             | 200 : OK      |

user
| 기능            | Method       | URL                   | Request        | reponse       | 상태코드      |
|-----------------|--------------|-----------------------|----------------|---------------|---------------|
| 유저 등록        | POST         | /users                | 요청 body      | 등록 정보      | 201 : Created |
| 유저 정보 조회   | GET          | /users/{id}           | 요청 param     | 단건 응답 정보 | 200 : OK      |
| 유저 정보 수정   | PATCH        | /users                | 요청 body      | 단건 응답 정보 | 200 : OK      |
| 유저 정보 삭제   | DELETE       | /users/{id}           | 요청 body      | -             | 200 : OK      |

--------------------
## 🧾 ERD
사용자가 등록한 일정 정보를 저장하고 관리하기 위한 `schedule`, `user` 테이블의 구조입니다.

![image](https://github.com/user-attachments/assets/f560008c-4e79-45b0-92bd-f79cc22731fc)

