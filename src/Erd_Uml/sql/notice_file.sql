/**********************************/
/* Table Name: 공지 첨부파일 */
/**********************************/
CREATE TABLE NOTICE_FILE(
		FILE_NO                       		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		FILE_NAME                     		VARCHAR2(100)		 NOT NULL,
		FILE_SIZE                     		NUMBER		 NOT NULL,
		FILE_EX                       		VARCHAR2(100)		 NOT NULL,
		FILE_SRC                      		VARCHAR2(100)		 NOT NULL,
		R_NO                          		VARCHAR2(255)		 NULL ,
		N_NO                          		NUMBER(9)		 NULL ,
  FOREIGN KEY (N_NO) REFERENCES NOTICE (N_NO)
);

COMMENT ON TABLE NOTICE_FILE is '공지 첨부파일';
COMMENT ON COLUMN NOTICE_FILE.FILE_NO is '파일 번호';
COMMENT ON COLUMN NOTICE_FILE.FILE_NAME is '첨부파일명';
COMMENT ON COLUMN NOTICE_FILE.FILE_SIZE is '첨부파일 크기';
COMMENT ON COLUMN NOTICE_FILE.FILE_EX is '확장자명';
COMMENT ON COLUMN NOTICE_FILE.FILE_SRC is '첨부파일 주소';
COMMENT ON COLUMN NOTICE_FILE.R_NO is '후기 번호';
COMMENT ON COLUMN NOTICE_FILE.N_NO is '공지사항 번호';