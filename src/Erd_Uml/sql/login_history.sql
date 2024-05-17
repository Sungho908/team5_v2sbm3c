/**********************************/
/* Table Name: 로그인내역 */
/**********************************/
CREATE TABLE LOGIN_HISTORY(
		LH_NO                         		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		LH_IP                         		VARCHAR2(15)		 NOT NULL,
		LH_DATE                       		DATE		 NULL ,
		M_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE LOGIN_HISTORY is '로그인내역';
COMMENT ON COLUMN LOGIN_HISTORY.LH_NO is '로그인내역 번호';
COMMENT ON COLUMN LOGIN_HISTORY.LH_IP is '로그인내역 아이피';
COMMENT ON COLUMN LOGIN_HISTORY.LH_DATE is '로그인내역 접속일';
COMMENT ON COLUMN LOGIN_HISTORY.M_NO is '멤버 번호';