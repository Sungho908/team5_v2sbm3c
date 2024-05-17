/**********************************/
/* Table Name: 신발 첨부파일 */
/**********************************/
CREATE TABLE SHOES_FILE(
		FILE_NO                       		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		FILE_NAME                     		VARCHAR2(100)		 NOT NULL,
		FILE_SIZE                     		NUMBER		 NOT NULL,
		FILE_EX                       		VARCHAR2(100)		 NOT NULL,
		FILE_SRC                      		VARCHAR2(100)		 NOT NULL,
		S_NO                          		NUMBER(9)		 NULL ,
  FOREIGN KEY (S_NO) REFERENCES SHOSE (S_NO)
);

COMMENT ON TABLE SHOES_FILE is '신발 첨부파일';
COMMENT ON COLUMN SHOES_FILE.FILE_NO is '파일 번호';
COMMENT ON COLUMN SHOES_FILE.FILE_NAME is '첨부파일명';
COMMENT ON COLUMN SHOES_FILE.FILE_SIZE is '첨부파일 크기';
COMMENT ON COLUMN SHOES_FILE.FILE_EX is '확장자명';
COMMENT ON COLUMN SHOES_FILE.FILE_SRC is '첨부파일 주소';
COMMENT ON COLUMN SHOES_FILE.S_NO is '신발 번호';