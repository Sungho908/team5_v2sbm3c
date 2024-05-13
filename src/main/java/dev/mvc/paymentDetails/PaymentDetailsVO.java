package dev.mvc.paymentDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE PAYMENT_DETAILS(
//    PD_NO                             VARCHAR2(255)    NOT NULL    PRIMARY KEY,
//    PD_AMOUNT                         NUMBER(9)    NOT NULL,
//    P_NO                              NUMBER(9)    NOT NULL,
//    O_NO                              NUMBER(9)    NOT NULL,
//  FOREIGN KEY (P_NO) REFERENCES PAYMENT (P_NO),
//  FOREIGN KEY (O_NO) REFERENCES OPTION (O_NO)
//);
public class PaymentDetailsVO {
  /** 주문상세번호 */
  private Integer pdNo;
  
  
  /** 주문 수량 */
  private Integer pdAmount;
  
  
  /** 주문번호 */
  private Integer pNo;
  
  
  /** 옵션 번호 */
  private Integer oNo;
}
