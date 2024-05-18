DROP TABLE SHOES CASCADE CONSTRAINTS; -- 자식 테이블을 무시하고삭제
DROP TABLE SHOES;

CREATE TABLE SHOES(
        S_NO                                NUMBER(9)         NOT NULL         PRIMARY KEY,
        C_NO                                NUMBER(9)         NOT NULL,
        M_NO                                NUMBER(9)         NOT NULL,
        S_TITLE                             VARCHAR2(100)      DEFAULT '-'    NOT NULL,
        S_BRAND                             VARCHAR2(100)         NOT NULL,
        S_RATING                            NUMBER                 NOT NULL,  
        S_CNT                               NUMBER(7)         DEFAULT 0         NOT NULL,
        S_PRICE                             NUMBER         NOT NULL,  
        S_DISCOUNT                          NUMBER         NOT NULL,  
        S_CONTENTS                          VARCHAR2(1000)         NOT NULL,  
        S_VISIBLE                           CHAR(1)       DEFAULT 'Y'         NOT NULL

);


DROP SEQUENCE SHOES_SEQ;

CREATE SEQUENCE SHOES_SEQ
    START WITH 1         -- 시작 번호
    INCREMENT BY 1       -- 증가값
    MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
    CACHE 2              -- 2번은 메모리에서만 계산
    NOCYCLE;             -- 다시 1부터 생성되는 것을 방지
       
       
SELECT * FROM SHOES;
       
INSERT INTO SHOES(S_NO, C_NO, M_NO, S_TITLE, S_BRAND, S_RATING, S_CNT, S_PRICE, S_DISCOUNT, S_CONTENTS, S_VISIBLE)
VALUES(SHOES_SEQ.nextval, 1, 1, '나이키 덩크로우 그레이', 'NIKE', 4, 0, 190000, 20, '흰색/회색 두가지 색으로 제작한....', 'Y');

INSERT INTO SHOES(S_NO, C_NO, M_NO, S_TITLE, S_BRAND, S_RATING, S_CNT, S_PRICE, S_DISCOUNT, S_CONTENTS, S_VISIBLE)
VALUES(SHOES_SEQ.nextval, 1, 1, '나이키 코르테즈', 'NIKE', 4, 0, 100000, 20, '깔끔하고 트렌디한 디자인으로 제작한....', 'Y');

INSERT INTO SHOES(S_NO, C_NO, M_NO, S_TITLE, S_BRAND, S_RATING, S_CNT, S_PRICE, S_DISCOUNT, S_CONTENTS, S_VISIBLE)
VALUES(SHOES_SEQ.nextval, 1, 1, '나이키 에어포스', 'NIKE', 4, 0, 110000, 20, '깔끔한 스타일의 에어포스....', 'Y');
