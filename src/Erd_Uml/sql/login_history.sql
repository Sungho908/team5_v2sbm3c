/**********************************/
/* Table Name: 로그인내역 */
/**********************************/
CREATE TABLE LOGIN_HISTORY(
		LOGIN_HISTORY_NO              		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		IP                            		VARCHAR2(15)		 NOT NULL,
		RDATE                          		DATE		 NULL ,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE LOGIN_HISTORY is '로그인내역';
COMMENT ON COLUMN LOGIN_HISTORY.LOGIN_HISTORY_NO is '로그인내역 번호';
COMMENT ON COLUMN LOGIN_HISTORY.IP is '로그인내역 아이피';
COMMENT ON COLUMN LOGIN_HISTORY.RDATE is '로그인내역 접속일';
COMMENT ON COLUMN LOGIN_HISTORY.MEMBERNO is '멤버 번호';
