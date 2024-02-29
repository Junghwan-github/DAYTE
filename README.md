![dayte](https://github.com/Junghwan-github/DAYTE/assets/148609097/dd841e3e-91e5-4238-98b0-70c293e7aaf0)

___
# 💚 DAYTE
> Spring Boot와 Spring MVC 패턴을 기반으로 Spring Security를 활용해 제작한 
> 대구 여행 일정 관리 프로젝트 **DAYTE** 입니다.<br>
> [👉🏻 Link here](http://docs.yi.or.kr:20080)
#### ✔ 개발 동기 및 개요
* 한눈에 대구 도심 곳곳 즐길 거리와 맛집의 **정보와 위치를 확인**할 수 있고
* 이용자들이 촬영한 **사진, 경험, 리뷰 등을 기록**하고
* **여행 경험을 타인과 공유**할 수 있는 `나만의 일정 관리 노트`
#### ✔ 기간
`2023.12.~ 2024.02.`
#### ✔ 팀 소개
`RIRICHILD` (6명)
___
# 💚 개발 기술 및 환경
| 분류 | 내용|
|:--:|--|
|FRAMEWORK|<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/3.2.1-515151?style=for-the-badge">&nbsp;<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"><img src="https://img.shields.io/badge/3.2.3-515151?style=for-the-badge">|
|LIBRARY|<img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white">|
|LANGUAGE|<img src="https://img.shields.io/badge/java-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"><img src="https://img.shields.io/badge/17-515151?style=for-the-badge">&nbsp;<img src="https://img.shields.io/badge/jsp-%23ED8B00?style=for-the-badge">&nbsp;<img src="https://img.shields.io/badge/mysql-%234479A1?style=for-the-badge&logo=mysql&logoColor=white"><br><img src="https://img.shields.io/badge/javascript-%23F7DF1E?style=for-the-badge&logo=javascript&logoColor=111">&nbsp;<img src="https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white">
|VCS|<img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/sourcetree-%230052CC?style=for-the-badge">
|IDE|<img src="https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white">
___
# 💚 Database ERD
![erd](https://github.com/Junghwan-github/DAYTE/assets/148609097/a0f51dfb-4d7a-40f5-9c08-20b93cce71ba)
___
# 💚 주요 기능 및 페이지 구성
#### ✔ 주요 기능
* 콘텐츠 검색
* 일정 등록 및 관리
* 커뮤니티 기능

#### ✔ 페이지 구성
`👨‍👩‍👧‍👦 사용자`
![사용자페이지](https://github.com/Junghwan-github/DAYTE/assets/148609097/9fc4d189-bbdd-4de0-9d35-7e470eaa8846)

`💻 관리자`
![관리자페이지](https://github.com/Junghwan-github/DAYTE/assets/148609097/0379fcc1-5f35-468c-bbad-0f1acb95f9fd)
___
# 💚 기능 소개
## 👨‍👩‍👧‍👦 사용자
### ✔ **콘텐츠 검색** / 키워드 검색
![전체검색](https://github.com/Junghwan-github/DAYTE/assets/148609097/4c3487d2-09d0-4c8f-906e-274d38b09e13)
* 메인페이지에서 키워드로 원하는 숙소, 맛집, 카페, 이벤트를 검색할 수 있습니다.
* 검색창에는 추천 콘텐츠가 랜덤으로 표시됩니다.
  
### ✔ **콘텐츠 조회** / (숙소, 맛집, 카페, 이벤트)
![콘텐츠조회](https://github.com/Junghwan-github/DAYTE/assets/148609097/fb28311b-2084-4745-9c42-59fe64c1401b)
* 메인페이지에서 각각 콘텐츠의 카드를 클릭하면 카테고리별 콘텐츠 리스트가 나타납니다.
* 필터로 원하는 지역을 선택하거나 태그로 콘텐츠를 조회할 수 있습니다.
* 상단 검색바 옆의 아이콘으로도 카테고리를 이동할 수 있습니다.
* 콘텐츠의 리뷰를 토대로 산출된 별점을 볼 수 있습니다.
  
### ✔ 콘텐츠 상세페이지
![콘텐츠상세](https://github.com/Junghwan-github/DAYTE/assets/148609097/47df4bd9-5c2f-4d7e-8057-cf7eac9fa999)
* 원하는 콘텐츠의 영업 정보와 위치 정보를 얻을 수 있습니다.
* 하단의 별점 리뷰와 관련된 다른 사람의 일정 후기를 볼 수 있습니다.
### ✔ 일정 관리 / 일정 등록
![일정등록1](https://github.com/Junghwan-github/DAYTE/assets/148609097/5cc95c2d-0376-4148-9e84-c76e3d27f25b)
![일정등록2](https://github.com/Junghwan-github/DAYTE/assets/148609097/d0c25653-f21c-4cfc-82e4-5f4f1fd6aef2)
* 날짜를 선택하여 일정을 등록합니다.
* 일자별 콘텐츠를 추가하고 지도 위에 표시되는 마커로 동선을 파악하여 추가된 콘텐츠의 순서를 수정할 수 있습니다.
  
### ✔ 일정 수정
![일정수정](https://github.com/Junghwan-github/DAYTE/assets/148609097/d935a2d0-29c0-4d21-b63d-a6bed0084984)
* 일정 상세보기에서 일자별 일정 흐름을 파악할 수 있고 수정을 하거나 삭제를 할 수 있습니다.
  
### ✔ 리얼리뷰 / 종료된 일정 리뷰 등록 / 내가 쓴 일정후기 /내가 쓴 리뷰
![리얼리뷰](https://github.com/Junghwan-github/DAYTE/assets/148609097/14eafb4c-f106-4a3c-af82-d7bb915f8ae7)
* 종료된 일정에는 리얼리뷰로 별점과 간단한 리뷰를 남길 수 있습니다.
* 리얼리뷰는 중복으로 작성할 수 없고 수정하거나 삭제할 수 있습니다.
* 내가 쓴 일정후기들과 리얼리뷰들을 조회할 수 있습니다.
  
### ✔ 일정후기 조회
![일정후기](https://github.com/Junghwan-github/DAYTE/assets/148609097/727b32db-dade-410f-83f6-4fb7ad835675)
* 최신순으로 다른 사람들이 작성한 일정 후기를 조회할 수 있습니다.
* 자신의 일정 후기를 등록할 수 있습니다.


## 💻 관리자
### ✔ 대시보드 / 주간 방문자 통계 / 신규회원 / 신규게시물
![대시보드](https://github.com/Junghwan-github/DAYTE/assets/148609097/5b188611-a2cb-4379-aa22-1aae66d88244)
* 1주간의 방문자 통계를 확인할 수 있으며 클릭 시 기간별 방문자 통계 메뉴로 이동할 수 있습니다.
* 신규 가입한 회원의 목록을 볼 수 있으며 클릭 시 회원관리 메뉴로 이동할 수 있습니다.
* 신규 등록된 일정후기 목록을 볼 수 있으며 클릭 시 일정후기 게시물 관리 메뉴로 이동할 수 있습니다.
### ✔ 회원관리 / 회원권한변경 / 기간별 방문자집계 / 접속자 현황
![회원관리](https://github.com/Junghwan-github/DAYTE/assets/148609097/21d031a4-3635-40a5-b152-00b158cd5dd3)
* 회원관리에서는 회원의 권한을 변경하거나 신고접수된 회원에게 제재를 할 수 있습니다.
* 아이디, 닉네임, 권한 등으로 회원을 검색할 수 있습니다.
* 최근 로그인날로부터 11개월이 된 회원에게는 자동으로 휴면 안내 메일이 발송됩니다.
* 방문자 집계에서는 기간별로 방문자 집계를 확인할 수 있습니다.
* 접속자현황에서는 현재 접속한 회원의 아이디를 확인할 수 있습니다.
### ✔ 게시물 관리 / 게시물 삭제 / 콘텐츠 등록 및 삭제 / 메인 콘텐츠 등록 / 문의 관리
![게시물관리](https://github.com/Junghwan-github/DAYTE/assets/148609097/f66af082-5e0a-4496-b0cc-6e3e30ef27c6)
* 게시물 관리에서는 등록된 일정후기를 검색하거나 삭제하여 관리할 수 있습니다.
* 콘텐츠 관리에서는 콘텐츠를 검색하거나 등록 또는 삭제를 할 수 있습니다.
* 메인 설정에서는 메인 페이지의 슬라이더 콘텐츠를 등록하여 관리할 수 있습니다.
* 1:1 문의로 들어온 문의는 관리자의 이메일로 접수가 되며, 사용자에게는 접수된 문의내용에 대한 안내메일이 발송됩니다.
* 소셜 문의는 챗봇으로 FAQ를 관리할 수 있고, 사용자가 상담원 연결을 선택할 시 관리자의 소셜 문의 계정으로 연결됩니다.
### ✔ 공지사항 관리 / 공지사항 등록 및 수정 / 필독 공지사항 설정
![공지사항등록및수정](https://github.com/Junghwan-github/DAYTE/assets/148609097/8564f6f3-ba77-45f3-a927-cf7f8cef7c5d)
* 공지사항 전체수정을 클릭할 시 전체 공지사항 구성에 대한 설정을 할 수 있습니다.
* 등록한 일반공지사항을 필독 공지사항으로 등록하고 공지사항이 보여질 순서를 조정할 수 있습니다.





