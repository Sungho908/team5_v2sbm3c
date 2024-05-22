/**********************************/
/* Table Name: 신고 */
/**********************************/
CREATE TABLE REPORT(
		REPORTNO                      		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		COMMENT                       		VARCHAR2(1000)		 NOT NULL,
		TYPENO                        		NUMBER(9)		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
		REVIEWNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO),
  FOREIGN KEY (TYPENO) REFERENCES REPORT_TYPE (TYPENO)
);

COMMENT ON TABLE REPORT is '신고';
COMMENT ON COLUMN REPORT.REPORTNO is '신고 번호';
COMMENT ON COLUMN REPORT.COMMENT is '신고 내용';
COMMENT ON COLUMN REPORT.TYPENO is '신고 유형번호 ';
COMMENT ON COLUMN REPORT.MEMBERNO is '멤버 번호';
COMMENT ON COLUMN REPORT.REVIEWNO is '후기 번호';