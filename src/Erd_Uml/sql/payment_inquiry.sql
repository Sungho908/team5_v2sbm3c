/**********************************/
/* Table Name: 주문 문의 */
/**********************************/
CREATE TABLE PAYMENT_INQUIRY(
		IN_NO                         		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		IN_COMMENT                    		VARCHAR2(1000)		 NOT NULL,
		IN_DATE                       		DATE		 NOT NULL,
		ANSWER_VISIBLE                		CHAR(1)		 NOT NULL,
		ANSWER_COMMENT                		VARCHAR2(1000)		 NULL ,
		M_NO                          		NUMBER(9)		 NOT NULL,
		P_NO                          		NUMBER(9)		 NULL ,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (P_NO) REFERENCES PAYMENT (P_NO)
);

COMMENT ON TABLE PAYMENT_INQUIRY is '주문 문의';
COMMENT ON COLUMN PAYMENT_INQUIRY.IN_NO is '주문 문의 번호';
COMMENT ON COLUMN PAYMENT_INQUIRY.IN_COMMENT is '문의 내용';
COMMENT ON COLUMN PAYMENT_INQUIRY.IN_DATE is '문의 작성일';
COMMENT ON COLUMN PAYMENT_INQUIRY.ANSWER_VISIBLE is '답변 여부';
COMMENT ON COLUMN PAYMENT_INQUIRY.ANSWER_COMMENT is '답변 내용';
COMMENT ON COLUMN PAYMENT_INQUIRY.M_NO is '멤버 번호';
COMMENT ON COLUMN PAYMENT_INQUIRY.P_NO is '주문 번호';