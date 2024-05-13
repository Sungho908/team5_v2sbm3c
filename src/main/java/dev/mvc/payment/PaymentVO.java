package dev.mvc.payment;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE PAYMENT(
//    P_NO                              NUMBER(9)    NOT NULL    PRIMARY KEY,
//    P_DATE                            DATE     NOT NULL,
//    P_STATUS                          VARCHAR2(50)     NOT NULL,
//    P_PAYMANT_STATUS                  VARCHAR2(50)     NOT NULL,
//    P_CS_STATUS                       VARCHAR2(50)     NULL ,
//    P_PRICE                           NUMBER     NOT NULL,
//    P_TOTAL_PRICE                     NUMBER     NULL ,
//    P_DELICERY                        NUMBER     NOT NULL,
//    P_TOTAL_PAYMENT                   NUMBER     NOT NULL,
//    M_NO                              NUMBER(9)    NOT NULL,
//  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
//);
public class PaymentVO {
  /** 주문번호 */
  private Integer pNo;
  
  
  /** 주문일자 */
  private Date pDate;
  
  
  /** 
   * 주문상태 <br>
   * 상품준비중 / 배송준비중 / 배송보류 / 배송대기 / 배송중 / 배송완료
   */
  private String pStatus;
  
  
  /** 결제상태 <br>
   * 입금전 / 추가입금대기 / 입금완료(수동) / 입금완료(자동) / 결제완료
   */
  private String pPaymentStatus;
  
  
  /** CS상태 <br>
   * 취소 / 교환 / 반품 / 환불 
   */
  private String pCsStatus;
  
  
  /** 상품금액 <br>
   *  판매가 * 상품수량
   */
  private Double pPrice;
  
  
  /** 총 상품금액 <br>
   *  상품금액들의 합
   */
  private Double pTotalPrice;
  
  
  /** 배송비 */
  private Double pDelicery;
  
  
  /** 총 주문금액 <br>
   *  총상품금액 + 배송비
   */
  private Double pTotalPayment;
  
  
  /** 멤버번호 */
  private Integer mNo;
}
