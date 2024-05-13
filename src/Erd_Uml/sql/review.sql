/**********************************/
/* Table Name: 후기 */
/**********************************/
CREATE TABLE REVIEW(
		R_NO                          		VARCHAR2(255)		 NOT NULL		 PRIMARY KEY,
		R_COMMENT                     		VARCHAR2(1000)		 NOT NULL,
		R_GRADE                       		NUMBER		 NOT NULL,
		S_NO                          		NUMBER(9)		 NOT NULL,
		U_NO                          		NUMBER(9)		 NOT NULL,
		R_DATE                        		DATE		 NOT NULL,
		LIKE_NO                       		NUMBER(9)		 NULL ,
  FOREIGN KEY (S_NO) REFERENCES SHOSE (S_NO),
  FOREIGN KEY (U_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE REVIEW is '후기';
COMMENT ON COLUMN REVIEW.R_NO is '후기 번호';
COMMENT ON COLUMN REVIEW.R_COMMENT is '후기 내용';
COMMENT ON COLUMN REVIEW.R_GRADE is '후기 점수';
COMMENT ON COLUMN REVIEW.S_NO is '신발 번호';
COMMENT ON COLUMN REVIEW.U_NO is '멤버 번호';
COMMENT ON COLUMN REVIEW.R_DATE is '후기 작성일';
COMMENT ON COLUMN REVIEW.LIKE_NO is '좋아요 번호';