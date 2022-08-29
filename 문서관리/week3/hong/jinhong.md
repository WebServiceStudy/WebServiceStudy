 3주차. 개인 정리 내용

### 목차
1. [MySQL 테이블 기초 초안]()
2. []()
3. []()
4. [참고자료]()

---

#### 1. MySQL 테이블 기초 초안
```Mysql
# 멤버 관련 테이블
CREATE TABLE member_table (
	mb_seq  	INT  		NOT NULL SUTO_INCREMENT, /* 시퀀스 - (자동 인덱스증가) */
	mb_id 		VARCHAR(20) 	NOT NULL, 		 /* 유저_ID */
	mb_pw 		VARCHAR(100) 	NOT NULL, 		 /* 유저_PW */
	mb_name 	VARCHAR(20) 	NOT NULL, 		 /* 유저_이름 */
	mb_email	VARCHAR(100),				 /* 유저_이메일 */
	mb_email_sms_yn VARCHAR(1),				 /* 유저_수신여부 */
	mb_sex 		VARCHAR(1) 	NOT NULL, 		 /* 유저_성별 */
	mb_address 	VARCHAR(100), 				 /* 유저_주소 */
	mb_zipcode	VARCHAR(30),				 /* 유저_우편번호 */
	mb_tell 	VARCHAR(11), 				 /* 유저_번호 */
	mb_Ins_date 	VARCHAR(8), 				 /* 유저_추가일 */
	mb_upd_date 	VARCHAR(8), 				 /* 유저_수정일 */
	mb_alias_name	VARCHAR(20)	NOT NULL,		 /* 유저_닉네임 */
	mb_photo	VARCHAR(100)				 /* 유저_프로필 */
	mb_account_lev 	VARCHAR(10) 				 /* 유저_등급 */
	mb_account_yn 	VARCHAR(1), 				 /* 유저_휴먼계정여부 */
	mb_lastlog_date VARCHAR(8)				 /* 유저_마지막_접속일 */
	mb_account_cnt  VARCHAR(10),				 /* 유저_휴먼_카운트 */
	mb_is_admin_yn 	VARCHAR(1),				 /* 유저_어드민_권한여부 */
	PRIMARY KEY(mb_seq) 					 /* 기본키 설정 */
) ENGINE=MYISAM CHARSET=utf8; /* MYISAM으로 지정하였고, CHARSET 은 utf-8로 지정하여 가변폭 방식의 인코딩으로 설정 */

# 신고, 블랙 멤버 관리 테이블
CREATE TABLE member_blame_table (
	mb_blame_seq	 INT  		NOT NULL SUTO_INCREMENT, /* 시퀀스 - (자동 인덱스증가) */
	mb_target_id	 VARCHAR(20)	NOT NULL,		 /* 신고_유저_아이디*/
	mb_id		 VARCHAR(20)	NOT NULL,		 /* 신고_당한_아이디*/
	mb_blame_cnt	 VARCHAR(1),				 /* 유저_경고_횟수 */
	mb_blame_reason  VARCHAR(100)	NOT NULL,		 /* 유저_경고_사유 */
	mb_blame_date	 VARCHAR(8)	NOT NULL,		 /* 유저_경고_일정 */
	mb_blame_cnt	 VARCHAR(10),	NOT NULL,		 /* 유저_경고_카운트*/
	mb_blame_stop_yn VARCHAR(1)	NOT NULL,		 /* 유저_경고_정지 */
	mb_blame_type	 VARCHAR(10)	NOT NULL	 	 /* 유저_경고_타입 */	
) ENGINE=MYISAM CHARSET=utf8;

# 아직 미정
CREATE TABLE member_?_table (

)ENGINE=MYISAM CHARSET=utf8;

# 어떤 게시판이 될지 모르겠지만 기본 틀
CREATE TABLE board_?_table (
	brd_seq		INT  		NOT NULL SUTO_INCREMENT, /* 시퀀스 - (자동 인덱스증가) */
	brd_type	VARCHAR(1)	NOT NULL,		 /* 게시판_타입 */
	brd_new_yn	VARCHAR(1)	NOT NULL,		 /* 게시판_신규등록 */	
	brd_Ins_date	VARCHAR(18)	NOT NULL,		 /* 게시판_등록일 */
	brd_upd_date	VARCHAR(18),		 		 /* 게시판_수정일 */
	brd_title	VARCHAR(100)	NOT NULL,		 /* 게시판_제목 */
	brd_writer	VARCHAR(20)	NOT NULL,		 /* 게시판_작성자 - IF익명이 있다면 NOT NULL 제거 ELSE 닉네임 적용 */
	brd_contents	BLOB,		 			/* 게시판_내용 */
	brd_see_cnt	INT		NOT NULL,		 /* 게시판_조회수 */
) ENGINE=MYISAM CHARSET=utf8;

# 댓글
CREATE TABLE reply_table (

) ENGINE=MYISAM CHARSET=utf8;
```

#### 참고자료
1. [[MySQL] BLOB vs TEXT]( ttps://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=sory1008&logNo=220932249450)
2. [MySQL 에 BLOB으로 이미지 저장하기](https://salix97.tistory.com/181)
3. [(MYSQL/SQL) blob (블랍) 이미지 파일 저장 테이블 생성 실시](https://kkh0977.tistory.com/2014)

