package dev.mvc.review;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
CREATE TABLE REVIEW(
    REVIEWNO                          NUMBER(9)    NOT NULL    PRIMARY KEY,
    CONTENTS                          VARCHAR2(1000)     NOT NULL,
    GRADE                             NUMBER     NOT NULL,
    RDATE                             DATE     NOT NULL,
    SHOESNO                           NUMBER(9)    NOT NULL,
    MEMBERNO                          NUMBER(9)    NOT NULL,
  FOREIGN KEY (SHOESNO) REFERENCES SHOES (SHOESNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);
*/

@Getter
@Setter
@ToString
public class ReviewVO {
  /** 후기 번호 */
  private Integer reviewno;
  
  
  /** 후기 내용 */
  private String contents;
  
  
  /** 후기 점수 */
  private Double grade;
  
  
  /** 후기 작성일 */
  private Date rDate;
  
  
  /** 신발 번호*/
  private Integer shoesno;
  
  
  /** 멤버 번호*/
  private Integer memberno;
  
  
  /** 좋아요 번호*/
  private Integer likeNo;
  
}
