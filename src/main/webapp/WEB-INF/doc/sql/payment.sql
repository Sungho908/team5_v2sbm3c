DROP TABLE PAYMENT CASCADE CONSTRAINTS;
DROP SEQUENCE PAYMENT_SEQ;

CREATE TABLE PAYMENT(
		PAYMENTNO                     		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		RDATE                         		DATE		 NOT NULL,
		STATUS                        		VARCHAR2(50)		 NOT NULL,
		PAYMENT_STATUS                		VARCHAR2(50)		 NOT NULL,
		CSSTATUS                      		VARCHAR2(50),
		PRICE                         		NUMBER(9)		 NOT NULL,
		TOTAL_PRICE                   		NUMBER(9),
		DELIVERY                      		NUMBER(9)	 NOT NULL,
		TOTAL_PAYMENT                 		NUMBER(9)	 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE PAYMENT is '주문';
COMMENT ON COLUMN PAYMENT.PAYMENTNO is '주문 번호';
COMMENT ON COLUMN PAYMENT.RDATE is '주문 일자';
COMMENT ON COLUMN PAYMENT.STATUS is '주문 상태';
COMMENT ON COLUMN PAYMENT.PAYMENT_STATUS is '결제 상태';
COMMENT ON COLUMN PAYMENT.CSSTATUS is 'CS 상태';
COMMENT ON COLUMN PAYMENT.PRICE is '상품금액';
COMMENT ON COLUMN PAYMENT.TOTAL_PRICE is '총 상품금액';
COMMENT ON COLUMN PAYMENT.DELIVERY is '배송비';
COMMENT ON COLUMN PAYMENT.TOTAL_PAYMENT is '총 주문금액';
COMMENT ON COLUMN PAYMENT.MEMBERNO is '멤버 번호';

CREATE SEQUENCE PAYMENT_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
 
INSERT INTO PAYMENT(paymentno, rdate, status, payment_status, csstatus, price, total_price, delivery, total_payment, memberno)
VALUES(PAYMENT_SEQ.nextval, sysdate, '배송 중', '결제 완료', '구매', 20000, 40000, 3000, 20000 + 40000 + 3000, 1);

INSERT INTO PAYMENT(paymentno, rdate, status, payment_status, csstatus, price, total_price, delivery, total_payment, memberno)
VALUES(PAYMENT_SEQ.nextval, sysdate, '배송 완료', '결제 완료', '구매', 50000, 150000, 6000, 50000 + 150000 + 6000, 1);

INSERT INTO PAYMENT(paymentno, rdate, status, payment_status, csstatus, price, total_price, delivery, total_payment, memberno)
VALUES(PAYMENT_SEQ.nextval, sysdate, '배송 전', '결제 전', '구매', 15000, 75000, 1000, 15000 + 75000 + 1000, 1);

SELECT paymentno, rdate, status, payment_status, csstatus, price, total_price, delivery, total_payment, memberno FROM PAYMENT;
