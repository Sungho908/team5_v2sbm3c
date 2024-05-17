/**********************************/
/* Table Name: 멤버 */
/**********************************/
CREATE TABLE MEMBER(
		MEMBERNO                      		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		ID                            		VARCHAR2(100)		 NOT NULL,
		PW                            		VARCHAR2(100)		 NOT NULL,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		NICKNAME                      		VARCHAR2(100)		 NOT NULL,
		PHONE                         		VARCHAR2(13)		 NOT NULL,
		EMAIL                         		VARCHAR2(50)		 NOT NULL,
		THUMB                         		VARCHAR2(100)		 NULL ,
		ADDR1                         		VARCHAR2(100)		 NULL ,
		ADDR2                         		VARCHAR2(100)		 NULL ,
		ZIPCODE                       		NUMBER(5)		 NULL ,
		DATE                          		DATE		 NULL ,
		RDATE                         		DATE		 NOT NULL,
		POINT                         		NUMBER(9)		 DEFAULT 0		 NULL ,
		GENDER                        		VARCHAR2(20)		 NOT NULL,
		GRADE                         		NUMBER(2)		 NULL ,
		ROLE                          		VARCHAR2(30)		 DEFAULT USER		 NOT NULL
);

COMMENT ON TABLE MEMBER is '멤버';
COMMENT ON COLUMN MEMBER.MEMBERNO is '멤버 번호';
COMMENT ON COLUMN MEMBER.ID is '멤버 아이디';
COMMENT ON COLUMN MEMBER.PW is '멤버 비밀번호';
COMMENT ON COLUMN MEMBER.NAME is '멤버 이름';
COMMENT ON COLUMN MEMBER.NICKNAME is '멤버 닉네임';
COMMENT ON COLUMN MEMBER.PHONE is '멤버 전화번호';
COMMENT ON COLUMN MEMBER.EMAIL is '멤버 이메일';
COMMENT ON COLUMN MEMBER.THUMB is '멤버 섬네일';
COMMENT ON COLUMN MEMBER.ADDR1 is '멤버 주소1';
COMMENT ON COLUMN MEMBER.ADDR2 is '멤버 상세주소';
COMMENT ON COLUMN MEMBER.ZIPCODE is '멤버 우편번호';
COMMENT ON COLUMN MEMBER.DATE is '멤버 생년월일';
COMMENT ON COLUMN MEMBER.RDATE is '멤버 가입일';
COMMENT ON COLUMN MEMBER.POINT is '멤버 포인트';
COMMENT ON COLUMN MEMBER.GENDER is '멤버 성별';
COMMENT ON COLUMN MEMBER.GRADE is '멤버 등급';
COMMENT ON COLUMN MEMBER.ROLE is '멤버 권한';
DROP SEQUENCE member_seq;

CREATE SEQUENCE member_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

INSERT INTO member(memberno, id, pw, name, nickname, phone, email, thumb, addr1, addr2, zipcode, date, rdate, point, gender, grade, role)
VALUES (member_seq.nextval, #{mId}, #{mPw}, #{mName}, #{mNickname}, #{mPhone}, #{mEmail}, #{mThumb}, #{mAddr1}, #{mAddr2}, #{mZipcode}, #{mDate}, sysdate, 0, #{mGender}, 1, 'USER')

select count(id) as cnt
from member
where id='9';

