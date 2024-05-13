/**********************************/
/* Table Name: 카테고리 */
/**********************************/
CREATE TABLE CATEGORY(
		C_NO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		C_NAME                        		VARCHAR2(100)		 NOT NULL,
		C_SUBNAME                     		VARCHAR2(100)		 NOT NULL,
		C_SEQ                         		NUMBER(9)		 NULL ,
		C_CNT                         		NUMBER(9)		 NOT NULL,
		C_PARENT                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (C_PARENT) REFERENCES CATEGORY (C_NO)
);

COMMENT ON TABLE CATEGORY is '카테고리';
COMMENT ON COLUMN CATEGORY.C_NO is '카테고리 번호';
COMMENT ON COLUMN CATEGORY.C_NAME is '카테고리 이름';
COMMENT ON COLUMN CATEGORY.C_SUBNAME is '카테고리 서브명';
COMMENT ON COLUMN CATEGORY.C_SEQ is '카테고리 출력순서';
COMMENT ON COLUMN CATEGORY.C_CNT is '카테고리 항목수';
COMMENT ON COLUMN CATEGORY.C_PARENT is '카테고리 부모번호';

