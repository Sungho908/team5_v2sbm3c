/**********************************/
/* Table Name: 장바구니 */
/**********************************/
CREATE TABLE BASKET(
		B_NO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		B_AMOUNT                      		NUMBER(9)		 NOT NULL,
		M_NO                          		NUMBER(9)		 NOT NULL,
		O_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (O_NO) REFERENCES OPTION (O_NO)
);

COMMENT ON TABLE BASKET is '장바구니';
COMMENT ON COLUMN BASKET.B_NO is '장바구니 번호';
COMMENT ON COLUMN BASKET.B_AMOUNT is '신발 수량';
COMMENT ON COLUMN BASKET.M_NO is '멤버 번호';
COMMENT ON COLUMN BASKET.O_NO is '옵션 번호';