/**********************************/
/* Table Name: 장바구니 */
/**********************************/
CREATE TABLE BASKET(
		BASKETNO                      		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		AMOUNT                        		NUMBER(9)		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
		OPTIONNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (OPTIONNO) REFERENCES OPTION (OPTIONNO)
);

COMMENT ON TABLE BASKET is '장바구니';
COMMENT ON COLUMN BASKET.BASKETNO is '장바구니 번호';
COMMENT ON COLUMN BASKET.AMOUNT is '신발 수량';
COMMENT ON COLUMN BASKET.MEMBERNO is '멤버 번호';
COMMENT ON COLUMN BASKET.OPTIONNO is '옵션 번호';