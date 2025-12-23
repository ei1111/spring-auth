# office-management-api

사내 사용자 관리, 캘린더 일정, 회의실 예약, 휴가 관리 기능을 제공하는  
**Spring Boot 기반 업무 관리 시스템 API**입니다.  
Spring Security 및 Kakao Social Login을 적용하여 안전한 인증 환경을 구성했습니다.

---

## 기술 스택

### Backend

- **Java:** 21  
- **Spring Boot:** 3.5.4  
- **Build Tool:** Gradle  
- **ORM:** Spring Data JPA  
- **Security:** Spring Security  
- **Web:** Spring MVC, WebFlux(혼합 사용)  

### Database / Cache

- **MySQL**
- **Redis**

### Authentication

- **ID / Password 로그인**
- **Kakao Social Login**
- **JWT 기반 인증**

### API Documentation

- **Swagger (SpringDoc OpenAPI)**

### Logging / Monitoring

- **P6Spy** – SQL 로그 확인
- **Spring Boot Actuator** – 애플리케이션 상태 모니터링

---

## 사용자 관리

### 사용자 등록

- 인사팀(HR)이 신규 입사자를 회원으로 등록

### 사용자 인증

- 사용자는 ID / 비밀번호를 통해 로그인
- Kakao Social Login 지원

---

## 기능 상세

### 캘린더 시스템

- 캘린더 일정 조회
- 캘린더 일정 추가
- 캘린더 일정 삭제

#### API

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/calendar` | 캘린더 일정 조회 |
| POST | `/calendar` | 캘린더 일정 추가 |
| DELETE | `/calendar` | 캘린더 일정 삭제 |

---

### 회의실 시스템

- 회의실 예약 조회
- 회의실 예약 생성
- 회의실 예약 삭제

#### API

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/meeting-rooms` | 회의실 예약 조회 |
| POST | `/meeting-rooms` | 회의실 예약 생성 |
| DELETE | `/meeting-rooms` | 회의실 예약 삭제 |

---

### 휴가 시스템

- 휴가 일정 조회
- 휴가 등록

#### API

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/vacations` | 휴가 일정 조회 |
| POST | `/vacations` | 휴가 등록 |

---

## 프로젝트 특징

- Spring Security 기반 인증 및 인가 처리
- Kakao Social Login 연동
- JWT 기반 Stateless 인증 구조
- Redis를 활용한 인증/세션 관련 확장 가능 구조
- RESTful API 설계
- P6Spy를 통한 SQL 실행 로그 가시화
- Actuator를 통한 애플리케이션 상태 확인

---
