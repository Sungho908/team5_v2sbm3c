/**********************************/
/* Table Name: 후기 */
/**********************************/
CREATE TABLE REVIEW(
		REVIEWNO                      		VARCHAR2(255)		 NOT NULL		 PRIMARY KEY,
		COMMENT                       		VARCHAR2(1000)		 NOT NULL,
		GRADE                         		NUMBER		 NOT NULL,
		DATE                          		DATE		 NOT NULL,
		SHOESNO                       		NUMBER(9)		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (SHOESNO) REFERENCES SHOES (SHOESNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE REVIEW is '후기';
COMMENT ON COLUMN REVIEW.REVIEWNO is '후기 번호';
COMMENT ON COLUMN REVIEW.COMMENT is '후기 내용';
COMMENT ON COLUMN REVIEW.GRADE is '후기 점수';
COMMENT ON COLUMN REVIEW.DATE is '후기 작성일';
COMMENT ON COLUMN REVIEW.SHOESNO is '신발 번호';
COMMENT ON COLUMN REVIEW.MEMBERNO is '멤버 번호';