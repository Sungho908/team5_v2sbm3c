package dev.mvc.like;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE LIKE(
//    LIKE_NO                           NUMBER(9)    NULL      PRIMARY KEY,
//    LIKE                              VARCHAR2(2)    NOT NULL,
//    R_NO                              VARCHAR2(255)    NULL ,
//  FOREIGN KEY (R_NO) REFERENCES REVIEW (R_NO)
//);
public class LikeVO {
  /** 좋아요 번호 */
  private Integer likeNo;
  
  
  /** 좋아요 싫어요 */
  private String like;
  
  
  /** 후기 번호 */
  private Integer rNo;
}
