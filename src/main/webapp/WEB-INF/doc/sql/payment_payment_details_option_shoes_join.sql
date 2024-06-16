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

SELECT m.memberno, p.paymentno
FROM member m
INNER JOIN payment p ON m.memberno = p.memberno
WHERE m.memberno = 1;


commit;

SELECT m.memberno,
       p.paymentno, p.rdate, p.status, p.payment_status, p.cs_status, p.total_price, p.delivery, p.total_payment,
       pd.payment_details_no, pd.payment_amount,
       s.shoesno, s.title, s.brand, s.rating, s.price, s.discount, s.contents, s.visible,
       sf.shoes_file_no, sf.name, sf.sizes as shoes_file_sizes, sf.ex, sf.src,
       o.optionno, o.sizes as option_sizes, o.amount, o.color
       
FROM member m 
    INNER JOIN payment p          ON m.memberno  = p.memberno
    INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
    INNER JOIN options o          ON pd.optionno = o.optionno
    INNER JOIN shoes s            ON o.shoesno   = s.shoesno
    INNER JOIN shoes_file sf      ON s.shoesno   = sf.shoesno
WHERE m.memberno = 1;

SELECT COUNT(p.paymentno) as cnt
FROM member m
INNER JOIN payment p ON m.memberno = p.paymentno
WHERE m.memberno = 1 and p.paymentno = 2;

SELECT COUNT(*) AS cnt
FROM member m 
    INNER JOIN payment p          ON m.memberno  = p.memberno
    INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
    INNER JOIN options o          ON pd.optionno = o.optionno
    INNER JOIN shoes s            ON o.shoesno   = s.shoesno
    INNER JOIN shoes_file sf      ON s.shoesno   = sf.shoesno
WHERE m.memberno = 1 AND p.paymentno = 1;

     
     
     SELECT m.memberno,
       p.paymentno, p.rdate, p.status, p.payment_status, p.cs_status, p.total_price, p.delivery, p.total_payment,
       LISTAGG(
         '{ "payment_details_no": ' || pd.payment_details_no || ', "payment_amount": ' || pd.payment_amount || 
         ', "optionno": ' || o.optionno || ', "sizes": ' || o.sizes || ', "amount": ' || o.amount || ', "color": ' || o.color || ' }',
         ','
       ) WITHIN GROUP (ORDER BY pd.payment_details_no) AS payment_details,
       s.shoesno, s.title, s.brand, s.rating, s.price, s.discount, s.contents, s.visible,
       sf.shoes_file_no, sf.name, sf.sizes as shoes_file_sizes, sf.ex, sf.src
FROM member m 
    INNER JOIN payment p          ON m.memberno  = p.memberno
    INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
    INNER JOIN options o          ON pd.optionno = o.optionno
    INNER JOIN shoes s            ON o.shoesno   = s.shoesno
    INNER JOIN shoes_file sf      ON s.shoesno   = sf.shoesno
WHERE m.memberno = 1
GROUP BY m.memberno, p.paymentno, p.rdate, p.status, p.payment_status, p.cs_status, p.total_price, p.delivery, p.total_payment,
         s.shoesno, s.title, s.brand, s.rating, s.price, s.discount, s.contents, s.visible,
         sf.shoes_file_no, sf.name, sf.sizes, sf.ex, sf.src;
         
         
         
         
     SELECT 
       p.paymentno, 
       p.rdate, 
       p.status,
       p.payment_status, 
       p.cs_status,
       p.total_price, 
       p.delivery, 
       p.total_payment,
       LISTAGG(
         '{ "payment_details_no": ' || pd.payment_details_no || ', "payment_amount": ' || pd.payment_amount || 
         ', "optionno": ' || o.optionno || ', "sizes": ' || o.sizes || ', "amount": ' || o.amount || ', "color": ' || o.color || ' }',
         ','
       ) WITHIN GROUP (ORDER BY pd.payment_details_no) AS payment_details,
       s.shoesno, s.title, s.brand, s.rating, s.price, s.discount, s.contents, s.visible,
       sf.shoes_file_no, sf.name, sf.sizes as shoes_file_sizes, sf.ex, sf.src
FROM member m 
    INNER JOIN payment p          ON m.memberno  = p.memberno
    INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
    INNER JOIN options o          ON pd.optionno = o.optionno
    INNER JOIN shoes s            ON o.shoesno   = s.shoesno
    INNER JOIN shoes_file sf      ON s.shoesno   = sf.shoesno
WHERE m.memberno = 1
GROUP BY m.memberno, p.paymentno, p.rdate, p.status, p.payment_status, p.cs_status, p.total_price, p.delivery, p.total_payment,
         s.shoesno, s.title, s.brand, s.rating, s.price, s.discount, s.contents, s.visible,
         sf.shoes_file_no, sf.name, sf.sizes, sf.ex, sf.src;










SELECT m.memberno,
           p.paymentno, 
           p.rdate, 
           p.status, 
           p.payment_status, 
           p.cs_status, 
           p.total_price, 
           p.delivery, 
           p.total_payment,
           
           
           LISTAGG(
             '{ "payment_details_no": ' || pd.payment_details_no || ', "payment_amount": ' || pd.payment_amount || 
             ', "optionno": ' || o.optionno || ', "sizes": ' || o.sizes || ', "amount": ' || o.amount || ', "color": ' || '"' || o.color || '"' || ' }',
             ','
           ) WITHIN GROUP (ORDER BY pd.payment_details_no) AS payment_details,
           s.shoesno, s.title, s.brand, s.rating, s.price, s.discount, s.contents, s.visible,
           sf.shoes_file_no, sf.name, sf.sizes as shoes_file_sizes, sf.ex, sf.src
    FROM member m 
        INNER JOIN payment p          ON m.memberno  = p.memberno
        INNER JOIN payment_details pd ON p.paymentno = pd.paymentno
        INNER JOIN options o          ON pd.optionno = o.optionno
        INNER JOIN shoes s            ON o.shoesno   = s.shoesno
        INNER JOIN shoes_file sf      ON s.shoesno   = sf.shoesno
    WHERE m.memberno =1
    GROUP BY m.memberno, p.paymentno, p.rdate, p.status, p.payment_status, p.cs_status, p.total_price, p.delivery, p.total_payment,
             s.shoesno, s.title, s.brand, s.rating, s.price, s.discount, s.contents, s.visible,
             sf.shoes_file_no, sf.name, sf.sizes, sf.ex, sf.src

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
