DROP TABLE SHOES CASCADE CONSTRAINTS;
DROP SEQUENCE SHOES_SEQ;

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
  -- FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
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



CREATE SEQUENCE SHOES_SEQ
    START WITH 1         -- 시작 번호
    INCREMENT BY 1       -- 증가값
    MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
    CACHE 2              -- 2번은 메모리에서만 계산
    NOCYCLE;             -- 다시 1부터 생성되는 것을 방지
       
       
SELECT * FROM SHOES;

       
INSERT INTO SHOES(S_NO, C_NO, M_NO, S_TITLE, S_BRAND, S_RATING, S_PRICE, S_DISCOUNT, S_CONTENTS, S_VISIBLE)
VALUES(SHOES_SEQ.nextval, 1, 1, '나이키 덩크로우 그레이', 'NIKE', 4, 190000, 20, '흰색/회색 두가지 색으로 제작한....', 'Y');

INSERT INTO SHOES(S_NO, C_NO, M_NO, S_TITLE, S_BRAND, S_RATING, S_PRICE, S_DISCOUNT, S_CONTENTS, S_VISIBLE)
VALUES(SHOES_SEQ.nextval, 1, 1, '나이키 코르테즈', 'NIKE', 4, 100000, 20, '깔끔하고 트렌디한 디자인으로 제작한....', 'Y');

INSERT INTO SHOES(S_NO, C_NO, M_NO, S_TITLE, S_BRAND, S_RATING, S_PRICE, S_DISCOUNT, S_CONTENTS, S_VISIBLE)
VALUES(SHOES_SEQ.nextval, 1, 1, '나이키 에어포스', 'NIKE', 4, 110000, 20, '깔끔한 스타일의 에어포스....', 'Y');


