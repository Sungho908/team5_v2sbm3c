/**********************************/
/* Table Name: 주문상세 */
/**********************************/
CREATE TABLE PAYMENT_DETAILS(
		PD_NO                         		VARCHAR2(255)		 NOT NULL		 PRIMARY KEY,
		PD_AMOUNT                     		NUMBER(9)		 NOT NULL,
		P_NO                          		NUMBER(9)		 NOT NULL,
		O_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (P_NO) REFERENCES PAYMENT (P_NO),
  FOREIGN KEY (O_NO) REFERENCES OPTION (O_NO)
);

COMMENT ON TABLE PAYMENT_DETAILS is '주문상세';
COMMENT ON COLUMN PAYMENT_DETAILS.PD_NO is '주문 상세번호';
COMMENT ON COLUMN PAYMENT_DETAILS.PD_AMOUNT is '주문 수량';
COMMENT ON COLUMN PAYMENT_DETAILS.P_NO is '주문 번호';
COMMENT ON COLUMN PAYMENT_DETAILS.O_NO is '옵션 번호';