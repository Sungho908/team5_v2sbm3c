/**********************************/
/* Table Name: 후기 첨부파일 */
/**********************************/
CREATE TABLE REVIEW_FILE(
		FILE_NO                       		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		FILE_NAME                     		VARCHAR2(100)		 NOT NULL,
		FILE_SIZE                     		NUMBER		 NOT NULL,
		FILE_EX                       		VARCHAR2(100)		 NOT NULL,
		FILE_SRC                      		VARCHAR2(100)		 NOT NULL,
		R_NO                          		VARCHAR2(255)		 NULL ,
  FOREIGN KEY (R_NO) REFERENCES REVIEW (R_NO)
);

COMMENT ON TABLE REVIEW_FILE is '후기 첨부파일';
COMMENT ON COLUMN REVIEW_FILE.FILE_NO is '파일 번호';
COMMENT ON COLUMN REVIEW_FILE.FILE_NAME is '첨부파일명';
COMMENT ON COLUMN REVIEW_FILE.FILE_SIZE is '첨부파일 크기';
COMMENT ON COLUMN REVIEW_FILE.FILE_EX is '확장자명';
COMMENT ON COLUMN REVIEW_FILE.FILE_SRC is '첨부파일 주소';
COMMENT ON COLUMN REVIEW_FILE.R_NO is '후기 번호';