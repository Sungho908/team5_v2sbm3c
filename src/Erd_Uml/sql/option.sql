/**********************************/
/* Table Name: 신발옵션 */
/**********************************/
CREATE TABLE OPTION(
		O_NO                          		NUMBER(9)		 NULL 		 PRIMARY KEY,
		O_SIZE                        		NUMBER(4)		 NOT NULL,
		O_AMOUNT                      		NUMBER(9)		 NOT NULL,
		O_COLOR                       		VARCHAR2(30)		 NOT NULL,
		S_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (S_NO) REFERENCES SHOSE (S_NO)
);

COMMENT ON TABLE OPTION is '신발옵션';
COMMENT ON COLUMN OPTION.O_NO is '옵션 번호';
COMMENT ON COLUMN OPTION.O_SIZE is '신발 사이즈';
COMMENT ON COLUMN OPTION.O_AMOUNT is '신발 재고';
COMMENT ON COLUMN OPTION.O_COLOR is '신발 색상';
COMMENT ON COLUMN OPTION.S_NO is '신발 번호';