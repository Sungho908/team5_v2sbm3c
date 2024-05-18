/**********************************/
/* Table Name: 신발옵션 */
/**********************************/
CREATE TABLE OPTION(
		OPTIONNO                          		NUMBER(9)		 NULL 		 PRIMARY KEY,
		SIZE                        		NUMBER(4)		 NOT NULL,
		AMOUNT                      		NUMBER(9)		 NOT NULL,
		COLOR                       		VARCHAR2(30)		 NOT NULL,
		SHOESNO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (S_NO) REFERENCES SHOSE (S_NO)
);

COMMENT ON TABLE OPTION is '신발옵션';
COMMENT ON COLUMN OPTION.O_NO is '옵션 번호';
COMMENT ON COLUMN OPTION.O_SIZE is '신발 사이즈';
COMMENT ON COLUMN OPTION.O_AMOUNT is '신발 재고';
COMMENT ON COLUMN OPTION.O_COLOR is '신발 색상';
COMMENT ON COLUMN OPTION.S_NO is '신발 번호';


CREATE SEQUENCE OPTION_SEQ
    START WITH 1         -- 시작 번호
    INCREMENT BY 1       -- 증가값
    MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
    CACHE 2              -- 2번은 메모리에서만 계산
    NOCYCLE;             -- 다시 1부터 생성되는 것을 방지
    
       
INSERT INTO OPTION(OPTIONNO, SIZE, AMOUNT, COLOR, SHOESNO)
VALUES(OPTION_SEQ.nextval, 270, 10, '회색/흰색', 1);
