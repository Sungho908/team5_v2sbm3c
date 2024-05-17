package dev.mvc.otherInquiry;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE OTHER_INQUIRY(
//    IN_NO                             NUMBER(9)    NOT NULL    PRIMARY KEY,
//    IN_COMMENT                        VARCHAR2(1000)     NOT NULL,
//    IN_DATE                           DATE     NOT NULL,
//    ANSWER_VISIBLE                    CHAR(1)    NOT NULL,
//    ANSWER_COMMENT                    VARCHAR2(1000)     NULL ,
//    M_NO                              NUMBER(9)    NOT NULL,
//  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
//);
public class OtherInquiryVO {
  /** 기타문의번호 */
  private Integer inNo;
  
  
  /** 기타문의내용 */
  private String inComment;
  
  
  /** 문의작성일 */
  private Date inDate;
  
  
  /** 답변여부 */
  private char answerVisible;
  
  
  /** 답변 내용 */
  private String answerComment;
  
  
  /** 멤버 번호 */
  private Integer mNo;
}
