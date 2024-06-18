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

    SELECT DISTINCT
      m.memberno,
      m.id AS memberid,
      m.nickname,
      p.paymentno
    FROM 
      member m 
      INNER JOIN payment p ON m.memberno = p.memberno

      WHERE UPPER(m.id) LIKE '%' || UPPER('') || '%' OR UPPER(m.nickname) LIKE '%' || UPPER('') || '%'

    ORDER BY 
      p.rdate DESC;
      



WITH PaymentRank AS (
  SELECT
    m.memberno,
    m.id AS memberid,
    m.nickname,
    p.paymentno,
    p.rdate,
    ROW_NUMBER() OVER (PARTITION BY m.memberno ORDER BY p.rdate DESC) AS rn
  FROM 
    member m 
    INNER JOIN payment p ON m.memberno = p.memberno
  WHERE 
    UPPER(m.id) LIKE '%' || UPPER('') || '%' 
    OR UPPER(m.nickname) LIKE '%' || UPPER('') || '%'
)
SELECT
  memberno,
  memberid,
  nickname,
  paymentno
FROM
  PaymentRank
WHERE
  rn = 1
ORDER BY 
  rdate DESC;



	  SELECT
      m.memberno,
      p.paymentno,
      pd.payment_details_no,
      pd.payment_amount,
      o.optionno,
      o.sizes,
      o.amount,
      o.color,
      s.shoesno,
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
      INNER JOIN payment p ON p.paymentno = m.memberno
      INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
      INNER JOIN options o ON pd.optionno = o.optionno
      INNER JOIN shoes s ON o.shoesno = s.shoesno
      LEFT JOIN shoes_file sf ON s.shoesno = sf.shoesno
    WHERE
      m.memberno = 17
    ORDER BY
      pd.payment_details_no

