/**********************************/
/* Table Name: 좋아요 */
/**********************************/
CREATE TABLE LIKE(
		LIKE_NO                       		NUMBER(9)		 NULL 		 PRIMARY KEY,
		LIKE                          		VARCHAR2(2)		 NOT NULL,
		R_NO                          		VARCHAR2(255)		 NULL ,
  FOREIGN KEY (R_NO) REFERENCES REVIEW (R_NO)
);

COMMENT ON TABLE LIKE is '좋아요';
COMMENT ON COLUMN LIKE.LIKE_NO is '좋아요 번호';
COMMENT ON COLUMN LIKE.LIKE is '좋아요';
COMMENT ON COLUMN LIKE.R_NO is '후기 번호';