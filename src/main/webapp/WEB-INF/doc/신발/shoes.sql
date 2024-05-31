DROP TABLE SHOES CASCADE CONSTRAINTS;
DROP SEQUENCE SHOES_SEQ;

CREATE TABLE SHOES(
		SHOESNO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		CATEGORYNO                          		NUMBER(9)		 NOT NULL,
		MEMBERNO                          		NUMBER(9)		 NOT NULL,
		TITLE                       		VARCHAR2(100)		 NOT NULL,
		BRAND                       		VARCHAR2(100)		 NOT NULL,
		RATING                      		NUMBER		 DEFAULT 0 NOT NULL,
		PRICE                       		NUMBER		 NOT NULL,
		DISCOUNT                    		NUMBER		 NOT NULL,
		CONTENTS                    		VARCHAR2(1000)		 NOT NULL,
		VISIBLE                     		CHAR(1)	     DEFAULT 'F' NOT NULL,
   FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
   FOREIGN KEY (CATEGORYNO) REFERENCES CATEGORY (CATEGORYNO)
);

COMMENT ON TABLE SHOES is '신발';
COMMENT ON COLUMN SHOES.SHOESNO is '신발 번호';
COMMENT ON COLUMN SHOES.CATEGORYNO is '카테고리 번호';
COMMENT ON COLUMN SHOES.MEMBERNO is '유저 번호';
COMMENT ON COLUMN SHOES.TITLE is '신발 제목';
COMMENT ON COLUMN SHOES.BRAND is '신발 브랜드명';
COMMENT ON COLUMN SHOES.RATING is '신발 평점';
COMMENT ON COLUMN SHOES.PRICE is '신발 가격';
COMMENT ON COLUMN SHOES.DISCOUNT is '신발 할인율';
COMMENT ON COLUMN SHOES.CONTENTS is '신발 설명';
COMMENT ON COLUMN SHOES.VISIBLE is '신발 판매 여부';


CREATE SEQUENCE SHOES_SEQ
    START WITH 1         -- 시작 번호
    INCREMENT BY 1       -- 증가값
    MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
    CACHE 2              -- 2번은 메모리에서만 계산
    NOCYCLE;             -- 다시 1부터 생성되는 것을 방지
       

       
INSERT INTO SHOES(SHOESNO, CATEGORYNO, MEMBERNO, TITLE, BRAND, RATING, PRICE, DISCOUNT, CONTENTS, VISIBLE)
VALUES(SHOES_SEQ.nextval, 1, 1, '나이키 덩크로우 그레이', '나이키', 4, 190000, 20, '흰색/회색 두가지 색으로 제작한....', 'F');

INSERT INTO SHOES(SHOESNO, CATEGORYNO, MEMBERNO, TITLE, BRAND, RATING, PRICE, DISCOUNT, CONTENTS, VISIBLE)
VALUES(SHOES_SEQ.nextval, 2, 1, '나이키 코르테즈', '나이키', 4, 100000, 20, '깔끔하고 트렌디한 디자인으로 제작한....', 'F');

INSERT INTO SHOES(SHOESNO, CATEGORYNO, MEMBERNO, TITLE, BRAND, RATING, PRICE, DISCOUNT, CONTENTS, VISIBLE)
VALUES(SHOES_SEQ.nextval, 3, 1, 'VLTN 신발', '발렌티노', 4, 433333, 20, '화려한 신발 발렌티노......', 'F');

INSERT INTO SHOES(SHOESNO, CATEGORYNO, MEMBERNO, TITLE, BRAND, PRICE, DISCOUNT, CONTENTS)
VALUES(SHOES_SEQ.nextval, 4, 1, '스포츠신발', '아디다스', 433333, 20, '편안한 아디다스....');

commit; 

select * from shoes;
--  검색(신발이 들어간 제목)
SELECT S_NO, C_NO, M_NO, S_TITLE, S_BRAND, S_RATING, S_PRICE, S_DISCOUNT, S_CONTENTS, S_VISIBLE
FROM shoes
WHERE S_TITLE LIKE ('%신발%')
ORDER BY S_NO ASC;

-- 검색(대소문자 관련 없음)
SELECT S_NO, C_NO, M_NO, S_TITLE, S_BRAND, S_RATING, S_PRICE, S_DISCOUNT, S_CONTENTS, S_VISIBLE
FROM shoes
WHERE UPPER(S_TITLE) LIKE UPPER('%VLTN%')
ORDER BY S_NO ASC;

-- 검색
SELECT S_NO, C_NO, M_NO, S_TITLE, S_BRAND, S_RATING, S_PRICE, S_DISCOUNT, S_CONTENTS, S_VISIBLE
FROM shoes
WHERE S_TITLE LIKE ('%신발%')
ORDER BY S_RATING DESC;

SELECT s.s_title FROM category c, shoes s
where c.c_no = s.c_no;

----검색 끝

-----수정 

UPDATE shoes
SET S_TITLE='VLTN 신발', S_BRAND='VLTN', S_RATING=4, S_PRICE=500000, S_DISCOUNT=15, S_VISIBLE='Y'
WHERE S_NO = 5;


select * from shoes;
----수정 끝


DELETE FROM shoes WHERE S_NO = 3;


