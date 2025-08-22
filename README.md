## 스프링 Security

### kakao socail login
사용자 관리: 인사팀은 신규입사자를 회원으로 등록합니다. 
사용자 인증: 사용자는 ID와 비밀번호를 입력하여 로그인 합니다.

기능
캘린더 시스템: 캘린더 일정 조회, 캘린더 일정 추가, 캘린더 일정 삭제
캘린더 일정 조회: GET /calendar
캘린더 일정 추가: POST /calendar
캘린더 일정 삭제: DELETE /calendar


회의실 시스템: 회의실 예약 조회, 회의실 예약 생성, 회의실 예약 삭제
회의실 예약 조회: GET /meeting-rooms
회의실 예약 생성: POST /meeting-rooms
회의실 예약 삭제: DELETE /meeting-rooms


휴가 시스템: 휴가 일정 조회, 휴가 등록
휴가 일정 조회: GET /vacaations
휴가 등록: POST /vacaations