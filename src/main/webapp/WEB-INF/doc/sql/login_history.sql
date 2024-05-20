/**********************************/
/* Table Name: 로그인내역 */
/**********************************/
DROP TABLE LOGIN_HISTORY;

DROP TABLE LOGIN_HISTORY CASCADE CONSTRAINTS;


CREATE TABLE LOGIN_HISTORY(
		LOGINNO              		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		IP                            		VARCHAR2(50)		 NOT NULL,
		RDATE                          		DATE		 NULL ,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE LOGIN_HISTORY is '로그인내역';
COMMENT ON COLUMN LOGIN_HISTORY.LOGINNO is '로그인내역 번호';
COMMENT ON COLUMN LOGIN_HISTORY.IP is '로그인내역 아이피';
COMMENT ON COLUMN LOGIN_HISTORY.RDATE is '로그인내역 접속일';
COMMENT ON COLUMN LOGIN_HISTORY.MEMBERNO is '멤버 번호';

DROP SEQUENCE LOGIN_SEQ;

CREATE SEQUENCE LOGIN_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;

-- c
INSERT INTO LOGIN_HISTORY(loginno, ip, rdate, memberno)
VALUES (login_seq.nextval, '192.0.0.1', sysdate, 1);
  
--r
SELECT COUNT(memberno) as cnt
FROM login_history
where memberno = 1;

--r 최근 날짜순 정렬
SELECT loginno, ip, rdate, memberno
FROM login_history
where memberno = 1
ORDER BY rdate desc;

--d 최근기록 10건만 남기고 나머지 삭제
DELETE FROM LOGIN_HISTORY
WHERE LOGINNO IN (
    SELECT LOGINNO
    FROM (
        SELECT LOGINNO,
               ROW_NUMBER() OVER (PARTITION BY MEMBERNO ORDER BY RDATE DESC) AS rnum
        FROM LOGIN_HISTORY
        WHERE MEMBERNO = 1
    ) subquery
    WHERE rnum > 10
);

DELETE FROM LOGIN_HISTORY
    WHERE LOGINNO IN (
      SELECT LOGINNO
      FROM (
        SELECT LOGINNO, ROW_NUMBER() OVER (ORDER BY RDATE DESC) AS rnum
        FROM LOGIN_HISTORY
        WHERE MEMBERNO = 1
      )
    WHERE rnum > 10 AND ROWNUM <= 1000



