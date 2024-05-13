/**********************************/
/* Table Name: 신발 */
/**********************************/
CREATE TABLE SHOES(
		S_NO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		C_NO                          		NUMBER(9)		 NOT NULL,
		M_NO                          		NUMBER(9)		 NOT NULL,
		S_TITLE                       		VARCHAR2(100)		 NOT NULL,
		S_BRAND                       		VARCHAR2(100)		 NOT NULL,
		S_RATING                      		NUMBER		 NOT NULL,
		S_PRICE                       		NUMBER		 NOT NULL,
		S_DISCOUNT                    		NUMBER		 NOT NULL,
		S_CONTENTS                    		VARCHAR2(1000)		 NOT NULL,
		S_VISIBLE                     		CHAR(1)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (C_NO) REFERENCES CATEGORY (C_NO)
);

COMMENT ON TABLE SHOES is '신발';
COMMENT ON COLUMN SHOES.S_NO is '신발 번호';
COMMENT ON COLUMN SHOES.C_NO is '카테고리 번호';
COMMENT ON COLUMN SHOES.M_NO is '유저 번호';
COMMENT ON COLUMN SHOES.S_TITLE is '신발 제목';
COMMENT ON COLUMN SHOES.S_BRAND is '신발 브랜드명';
COMMENT ON COLUMN SHOES.S_RATING is '신발 평점';
COMMENT ON COLUMN SHOES.S_PRICE is '신발 가격';
COMMENT ON COLUMN SHOES.S_DISCOUNT is '신발 할인율';
COMMENT ON COLUMN SHOES.S_CONTENTS is '신발 설명';
COMMENT ON COLUMN SHOES.S_VISIBLE is '신발 판매 여부';
