/**********************************/
/* Table Name: 멤버 */
/**********************************/
DROP TABLE member;

DROP TABLE member CASCADE CONSTRAINTS;

CREATE TABLE MEMBER(
		M_NO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		M_ID                          		VARCHAR2(100)		 NOT NULL,
		M_PW                          		VARCHAR2(100)		 NOT NULL,
		M_NAME                        		VARCHAR2(100)		 NOT NULL,
		M_NICKNAME                    		VARCHAR2(100)		 NOT NULL,
		M_PHONE                       		VARCHAR2(13)		 NOT NULL,
		M_EMAIL                       		VARCHAR2(50)		 NOT NULL,
		M_THUMB                       		VARCHAR2(100)		 NULL ,
		M_ADDR1                       		VARCHAR2(100)		 NULL ,
		M_ADDR2                       		VARCHAR2(100)		 NULL ,
		M_ZIPCODE                     		NUMBER(5)		 NULL ,
		M_DATE                        		DATE		 NULL ,
		M_RDATE                       		DATE		 NOT NULL,
		M_POINT                       		NUMBER(9)		 DEFAULT 0		 NULL ,
		M_GENDER                      		VARCHAR2(20)		 NOT NULL,
		M_GRADE                       		NUMBER(2)		 NULL ,
		M_ROLE                        		VARCHAR2(30)		 DEFAULT USER		 NOT NULL
);

COMMENT ON TABLE MEMBER is '멤버';
COMMENT ON COLUMN MEMBER.M_NO is '멤버 번호';
COMMENT ON COLUMN MEMBER.M_ID is '멤버 아이디';
COMMENT ON COLUMN MEMBER.M_PW is '멤버 비밀번호';
COMMENT ON COLUMN MEMBER.M_NAME is '멤버 이름';
COMMENT ON COLUMN MEMBER.M_NICKNAME is '멤버 닉네임';
COMMENT ON COLUMN MEMBER.M_PHONE is '멤버 전화번호';
COMMENT ON COLUMN MEMBER.M_EMAIL is '멤버 이메일';
COMMENT ON COLUMN MEMBER.M_THUMB is '멤버 섬네일';
COMMENT ON COLUMN MEMBER.M_ADDR1 is '멤버 주소1';
COMMENT ON COLUMN MEMBER.M_ADDR2 is '멤버 상세주소';
COMMENT ON COLUMN MEMBER.M_ZIPCODE is '멤버 우편번호';
COMMENT ON COLUMN MEMBER.M_DATE is '멤버 생년월일';
COMMENT ON COLUMN MEMBER.M_RDATE is '멤버 가입일';
COMMENT ON COLUMN MEMBER.M_POINT is '멤버 포인트';
COMMENT ON COLUMN MEMBER.M_GENDER is '멤버 성별';
COMMENT ON COLUMN MEMBER.M_GRADE is '멤버 등급';
COMMENT ON COLUMN MEMBER.M_ROLE is '멤버 권한';

DROP SEQUENCE member_seq;

CREATE SEQUENCE member_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

INSERT INTO member(m_no, m_id, m_pw, m_name, m_nickname, m_phone, m_email, m_thumb, m_addr1, m_addr2, m_zipcode, m_date, m_rdate, m_point, m_gender, m_grade, m_role)
VALUES (member_seq.nextval, #{mId}, #{mPw}, #{mName}, #{mNickname}, #{mPhone}, #{mEmail}, #{mThumb}, #{mAddr1}, #{mAddr2}, #{mZipcode}, #{mDate}, sysdate, 0, #{mGender}, 1, 'USER')

select count(m_id) as cnt
from member
where m_id='9';

