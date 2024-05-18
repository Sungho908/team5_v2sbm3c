/**********************************/
/* Table Name: 주문 */
/**********************************/
CREATE TABLE PAYMENT(
		PAYMENTNO                     		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		DATE                          		DATE		 NOT NULL,
		STATUS                        		VARCHAR2(50)		 NOT NULL,
		PAYMANT_STATUS                		VARCHAR2(50)		 NOT NULL,
		CSSTATUS                      		VARCHAR2(50)		 NULL ,
		PRICE                         		NUMBER		 NOT NULL,
		TOTAL_PRICE                   		NUMBER		 NULL ,
		DELIVERY                      		NUMBER		 NOT NULL,
		TOTAL_PAYMENT                 		NUMBER		 NOT NULL,
		MEMBERNO                      		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE PAYMENT is '주문';
COMMENT ON COLUMN PAYMENT.PAYMENTNO is '주문 번호';
COMMENT ON COLUMN PAYMENT.DATE is '주문 일자';
COMMENT ON COLUMN PAYMENT.STATUS is '주문 상태';
COMMENT ON COLUMN PAYMENT.PAYMANT_STATUS is '결제 상태';
COMMENT ON COLUMN PAYMENT.CSSTATUS is 'CS 상태';
COMMENT ON COLUMN PAYMENT.PRICE is '상품금액';
COMMENT ON COLUMN PAYMENT.TOTAL_PRICE is '총 상품금액';
COMMENT ON COLUMN PAYMENT.DELIVERY is '배송비';
COMMENT ON COLUMN PAYMENT.TOTAL_PAYMENT is '총 주문금액';
COMMENT ON COLUMN PAYMENT.MEMBERNO is '멤버 번호';
