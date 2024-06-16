CREATE TABLE PAYMENT(
		PAYMENTNO                     		NUMBER(9)		     NOT NULL		 PRIMARY KEY,
		RDATE                          		DATE		         NOT NULL,
		STATUS                        		VARCHAR2(50)		 NOT NULL,
		PAYMENT_STATUS                		VARCHAR2(50)		 NOT NULL,
		CS_STATUS                      		VARCHAR2(50)		 NULL ,
		TOTAL_PRICE                         NUMBER		         DEFAULT 0       NULL  , 
		DELIVERY                      		NUMBER		         DEFAULT 2500    NULL,
		TOTAL_PAYMENT                 		NUMBER		         DEFAULT 0       NOT NULL,
		MEMBERNO                      		NUMBER(9)		     NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

CREATE TABLE PAYMENT_DETAILS(
		PAYMENT_DETAILS_NO            		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		PAYMENT_AMOUNT                      NUMBER(9)		 NOT NULL,
		OPTIONNO                      		NUMBER(9)		 NOT NULL,
		PAYMENTNO                     		NUMBER(9)		 NULL ,
  FOREIGN KEY (OPTIONNO) REFERENCES OPTIONS (OPTIONNO),
  FOREIGN KEY (PAYMENTNO) REFERENCES PAYMENT (PAYMENTNO)
);

CREATE TABLE OPTIONs(
		OPTIONNO                      		NUMBER(9)		 NOT NULL 		 PRIMARY KEY,
		SIZE                          		NUMBER(4)		 NOT NULL,
		AMOUNT                        		NUMBER(9)		 NOT NULL,
		COLOR                         		VARCHAR2(30)		 NOT NULL,
		SHOESNO                       		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (SHOESNO) REFERENCES SHOES (SHOESNO)
);

CREATE TABLE SHOES(
		SHOESNO                       		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		CATEGORYNO                    		NUMBER(9)		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
		TITLE                         		VARCHAR2(100)		 NOT NULL,
		BRAND                         		VARCHAR2(100)		 NOT NULL,
		RATING                        		NUMBER		 NOT NULL,
		PRICE                         		NUMBER		 NOT NULL,
		DISCOUNT                      		NUMBER		 NOT NULL,
		CONTENTS                      		VARCHAR2(1000)		 NOT NULL,
		VISIBLE                       		CHAR(1)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (CATEGORYNO) REFERENCES CATEGORY (CATEGORYNO)
);

CREATE TABLE SHOES_FILE(
		SHOES_FILE_NO                 		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SIZES                          		NUMBER		 NOT NULL,
		EX                            		VARCHAR2(100)		 NOT NULL,
		SRC                           		VARCHAR2(100)		 NOT NULL,
		SHOESNO                       		NUMBER(9)		 NULL ,
  FOREIGN KEY (SHOESNO) REFERENCES SHOES (SHOESNO)
);
-----------------------------------------------------------------------------------------------------------------------
SELECT m.memberno, p.paymentno
FROM member m
INNER JOIN payment p ON m.memberno = p.memberno
WHERE m.memberno = 1;


commit;



     
 
         



  SELECT DISTINCT
    m.memberno,
    p.paymentno, 
    p.rdate, 
    p.status, 
    p.payment_status, 
    p.cs_status, 
    p.total_price, 
    p.delivery, 
    p.total_payment,
    o.shoesno, 
    s.title, 
    s.brand, 
    s.rating, 
    s.price, 
    s.discount, 
    s.contents, 
    s.visible,
    sf.shoes_file_no, 
    sf.name, 
    sf.sizes as shoes_file_sizes, 
    sf.ex, 
    sf.src
  FROM 
    member m 
    INNER JOIN payment p ON m.memberno = p.memberno
    INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
    INNER JOIN options o ON pd.optionno = o.optionno
    INNER JOIN shoes s ON o.shoesno = s.shoesno
    LEFT JOIN shoes_file sf ON s.shoesno = sf.shoesno
  WHERE 
    m.memberno = 1
  ORDER BY 
    p.rdate ASC;
    
    
    
    SELECT DISTINCT
    m.memberno,
    m.id,
    m.name,
    p.paymentno, 
    p.rdate, 
    p.status, 
    p.payment_status, 
    p.cs_status, 
    p.total_price, 
    p.delivery, 
    p.total_payment,
    o.shoesno, 
    s.title, 
    s.brand, 
    s.rating, 
    s.price, 
    s.discount, 
    s.contents, 
    s.visible,
    sf.shoes_file_no, 
    sf.name, 
    sf.sizes as shoes_file_sizes, 
    sf.ex, 
    sf.src
  FROM 
    member m 
    INNER JOIN payment p ON m.memberno = p.memberno
    INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
    INNER JOIN options o ON pd.optionno = o.optionno
    INNER JOIN shoes s ON o.shoesno = s.shoesno
    LEFT JOIN shoes_file sf ON s.shoesno = sf.shoesno

  ORDER BY 
    p.rdate ASC;

select * from member order by memberno;

select * from payment order by paymentno;

select * from payment_details order by payment_details_no;

select * from shoes order by shoesno;

-- PAYMENT 테이블 삽입문
DECLARE
  CURSOR member_cursor IS SELECT MEMBERNO FROM MEMBER;
  v_MEMBERNO MEMBER.MEMBERNO%TYPE;
BEGIN
  OPEN member_cursor;
  LOOP
    FETCH member_cursor INTO v_MEMBERNO;
    EXIT WHEN member_cursor%NOTFOUND;
    FOR j IN 1..3 LOOP
      INSERT INTO PAYMENT (PAYMENTNO, RDATE, STATUS, PAYMENT_STATUS, CS_STATUS, TOTAL_PRICE, DELIVERY, TOTAL_PAYMENT, MEMBERNO)
      VALUES (
        payment_seq.nextval,
        TO_DATE('2024-06-' || TO_CHAR(10 + j) || ' 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 
        CASE 
          WHEN j = 1 THEN '배송완료' 
          WHEN j = 2 THEN '상품준비중' 
          ELSE '배송중' 
        END, 
        '결제완료', 
        '반품', 
        100000 * j, 
        2500, 
        100000 * j + 2500, 
        v_MEMBERNO
      );
    END LOOP;
  END LOOP;
  CLOSE member_cursor;
END;


-- PAYMENT_DETAILS 테이블 삽입문
DECLARE
  CURSOR payment_cursor IS SELECT PAYMENTNO FROM PAYMENT;
  v_PAYMENTNO PAYMENT.PAYMENTNO%TYPE;
BEGIN
  OPEN payment_cursor;
  LOOP
    FETCH payment_cursor INTO v_PAYMENTNO;
    EXIT WHEN payment_cursor%NOTFOUND;
    FOR j IN 1..2 LOOP
      INSERT INTO PAYMENT_DETAILS (PAYMENT_DETAILS_NO, PAYMENT_AMOUNT, OPTIONNO, PAYMENTNO)
      VALUES (
        payment_details_seq.nextval,
        1, 
        j, 
        v_PAYMENTNO
      );
    END LOOP;
  END LOOP;
  CLOSE payment_cursor;
END;

commit;




