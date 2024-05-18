/**********************************/
/* Table Name: 신고 */
/**********************************/
CREATE TABLE REPORT(
		REPORT_NO                     		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		REPORT_COMMENT                		VARCHAR2(1000)		 NOT NULL,
		REPORT_TYPE_NO                		NUMBER(9)		 NOT NULL,
		M_NO                          		NUMBER(9)		 NOT NULL,
		R_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (R_NO) REFERENCES REVIEW (R_NO),
  FOREIGN KEY (REPORT_TYPE_NO) REFERENCES REPORT_TYPE (REPORT_TYPE_NO)
);

COMMENT ON TABLE REPORT is '신고';
COMMENT ON COLUMN REPORT.REPORT_NO is '신고 번호';
COMMENT ON COLUMN REPORT.REPORT_COMMENT is '신고 내용';
COMMENT ON COLUMN REPORT.REPORT_TYPE_NO is '신고 유형번호 ';
COMMENT ON COLUMN REPORT.M_NO is '멤버 번호';
COMMENT ON COLUMN REPORT.R_NO is '후기 번호';