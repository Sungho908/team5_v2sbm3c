/**********************************/
/* Table Name: 신발 문의 */
/**********************************/
CREATE TABLE SHOES_INQUIRY(
		IN_NO                         		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		IN_COMMENT                    		VARCHAR2(1000)		 NOT NULL,
		IN_DATE                       		DATE		 NOT NULL,
		ANSWER_VISIBLE                		CHAR(1)		 NOT NULL,
		ANSWER_COMMENT                		VARCHAR2(1000)		 NOT NULL,
		O_NO                          		NUMBER(9)		 NULL ,
		M_NO                          		NUMBER(9)		 NULL ,
  FOREIGN KEY (O_NO) REFERENCES OPTION (O_NO),
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE SHOES_INQUIRY is '신발 문의';
COMMENT ON COLUMN SHOES_INQUIRY.IN_NO is '신발 문의 번호';
COMMENT ON COLUMN SHOES_INQUIRY.IN_COMMENT is '문의 내용';
COMMENT ON COLUMN SHOES_INQUIRY.IN_DATE is '문의 작성일';
COMMENT ON COLUMN SHOES_INQUIRY.ANSWER_VISIBLE is '답변 여부';
COMMENT ON COLUMN SHOES_INQUIRY.ANSWER_COMMENT is '답변 내용';
COMMENT ON COLUMN SHOES_INQUIRY.O_NO is '옵션 번호';
COMMENT ON COLUMN SHOES_INQUIRY.M_NO is '멤버 번호';