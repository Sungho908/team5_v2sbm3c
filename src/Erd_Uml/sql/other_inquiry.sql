/**********************************/
/* Table Name: 기타 문의 */
/**********************************/
CREATE TABLE OTHER_INQUIRY(
		IN_NO                         		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		IN_COMMENT                    		VARCHAR2(1000)		 NOT NULL,
		IN_DATE                       		DATE		 NOT NULL,
		ANSWER_VISIBLE                		CHAR(1)		 NOT NULL,
		ANSWER_COMMENT                		VARCHAR2(1000)		 NULL ,
		M_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE OTHER_INQUIRY is '기타 문의';
COMMENT ON COLUMN OTHER_INQUIRY.IN_NO is '기타 문의 번호';
COMMENT ON COLUMN OTHER_INQUIRY.IN_COMMENT is '문의 내용';
COMMENT ON COLUMN OTHER_INQUIRY.IN_DATE is '문의 작성일';
COMMENT ON COLUMN OTHER_INQUIRY.ANSWER_VISIBLE is '답변 여부';
COMMENT ON COLUMN OTHER_INQUIRY.ANSWER_COMMENT is '답변 내용';
COMMENT ON COLUMN OTHER_INQUIRY.M_NO is '멤버 번호';