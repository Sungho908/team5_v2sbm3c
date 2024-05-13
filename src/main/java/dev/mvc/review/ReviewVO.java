package dev.mvc.review;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE REVIEW(
//    R_NO                              VARCHAR2(255)    NOT NULL    PRIMARY KEY,
//    R_COMMENT                         VARCHAR2(1000)     NOT NULL,
//    R_GRADE                           NUMBER     NOT NULL,
//    S_NO                              NUMBER(9)    NOT NULL,
//    U_NO                              NUMBER(9)    NOT NULL,
//    R_DATE                            DATE     NOT NULL,
//    LIKE_NO                           NUMBER(9)    NULL ,
//  FOREIGN KEY (S_NO) REFERENCES SHOSE (S_NO),
//  FOREIGN KEY (U_NO) REFERENCES MEMBER (M_NO)
//);
public class ReviewVO {
  /** 후기 번호 */
  private Integer rNo;
  
  
  /** 후기 내용 */
  private String rComment;
  
  
  /** 후기 점수 */
  private Double rGrade;
  
  
  /** 후기 작성일*/
  private Date rDate;
  
  
  /** 멤버 번호*/
  private Integer mNo;
  
  
  /** 신발 번호*/
  private Integer sNo;
  
  
  /** 좋아요 번호*/
  private Integer likeNo;
  
}
