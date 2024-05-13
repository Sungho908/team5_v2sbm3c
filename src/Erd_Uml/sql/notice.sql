/**********************************/
/* Table Name: 공지사항 */
/**********************************/
CREATE TABLE NOTICE(
		N_NO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		N_COMMENT                     		VARCHAR2(1000)		 NOT NULL,
		N_DATE                        		DATE		 NOT NULL,
		N_FILE                        		VARCHAR2(100)		 NOT NULL,
		N_SIZE                        		NUMBER(9)		 NOT NULL,
		M_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE NOTICE is '공지사항';
COMMENT ON COLUMN NOTICE.N_NO is '공지사항 번호';
COMMENT ON COLUMN NOTICE.N_COMMENT is '공지사항 내용';
COMMENT ON COLUMN NOTICE.N_DATE is '공지사항 작성일';
COMMENT ON COLUMN NOTICE.N_FILE is '첨부 파일';
COMMENT ON COLUMN NOTICE.N_SIZE is '첨부 파일 크기';
COMMENT ON COLUMN NOTICE.M_NO is '멤버 번호';