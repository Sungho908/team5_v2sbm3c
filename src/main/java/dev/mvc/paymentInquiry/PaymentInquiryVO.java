package dev.mvc.paymentInquiry;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE PAYMENT_INQUIRY(
//    IN_NO                             NUMBER(9)    NOT NULL    PRIMARY KEY,
//    IN_COMMENT                        VARCHAR2(1000)     NOT NULL,
//    IN_DATE                           DATE     NOT NULL,
//    ANSWER_VISIBLE                    CHAR(1)    NOT NULL,
//    ANSWER_COMMENT                    VARCHAR2(1000)     NULL ,
//    M_NO                              NUMBER(9)    NOT NULL,
//    P_NO                              NUMBER(9)    NULL ,
//  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
//  FOREIGN KEY (P_NO) REFERENCES PAYMENT (P_NO)
//);
public class PaymentInquiryVO {
  /** 주문문의번호 */
  private Integer inNo;
  
  
  /** 문의 내용 */
  private String inComment;
  
  
  /** 문의 작성일 */
  private Date inDate;
  
  
  /** 답변 여부 */
  private char answerVisible;
  
  
  /** 답변 내용 */
  private String answerComment;
  
  
  /** 멤버 번호 */
  private Integer mNo;
  
  
  /** 주문 번호 */
  private Integer pNo;
}
