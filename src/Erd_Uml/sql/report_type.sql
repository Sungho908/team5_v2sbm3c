/**********************************/
/* Table Name: 신고 유형 */
/**********************************/
CREATE TABLE REPORT_TYPE(
		REPORT_TYPE_NO                		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		REPORT_TYPE                   		VARCHAR2(100)		 NOT NULL
);

COMMENT ON TABLE REPORT_TYPE is '신고 유형';
COMMENT ON COLUMN REPORT_TYPE.REPORT_TYPE_NO is '신고 유형번호';
COMMENT ON COLUMN REPORT_TYPE.REPORT_TYPE is '신고 유형';