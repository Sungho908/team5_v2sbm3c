/**********************************/
/* Table Name: 주문 */
/**********************************/
CREATE TABLE PAYMENT(
		P_NO                          		NUMBER(9)		 NOT NULL		 PRIMARY KEY,
		P_DATE                        		DATE		 NOT NULL,
		P_STATUS                      		VARCHAR2(50)		 NOT NULL,
		P_PAYMANT_STATUS              		VARCHAR2(50)		 NOT NULL,
		P_CS_STATUS                   		VARCHAR2(50)		 NULL ,
		P_PRICE                       		NUMBER		 NOT NULL,
		P_TOTAL_PRICE                 		NUMBER		 NULL ,
		P_DELICERY                    		NUMBER		 NOT NULL,
		P_TOTAL_PAYMENT               		NUMBER		 NOT NULL,
		M_NO                          		NUMBER(9)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE PAYMENT is '주문';
COMMENT ON COLUMN PAYMENT.P_NO is '주문 번호';
COMMENT ON COLUMN PAYMENT.P_DATE is '주문 일자';
COMMENT ON COLUMN PAYMENT.P_STATUS is '주문 상태';
COMMENT ON COLUMN PAYMENT.P_PAYMANT_STATUS is '결제 상태';
COMMENT ON COLUMN PAYMENT.P_CS_STATUS is 'CS 상태';
COMMENT ON COLUMN PAYMENT.P_PRICE is '상품금액';
COMMENT ON COLUMN PAYMENT.P_TOTAL_PRICE is '총 상품금액';
COMMENT ON COLUMN PAYMENT.P_DELICERY is '배송비';
COMMENT ON COLUMN PAYMENT.P_TOTAL_PAYMENT is '총 주문금액';
COMMENT ON COLUMN PAYMENT.M_NO is '멤버 번호';