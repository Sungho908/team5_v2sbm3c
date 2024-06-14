package dev.mvc.likes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE LIKES(
//    LIKESNO                           NUMBER(9)    NULL      PRIMARY KEY,
//    LIKES                                 NUMBER(9) DEFAULT 0  NOT NULL,
//    HATES                                 NUMBER(9) DEFAULT 0  NOT NULL,
//    REVIEWNO                      NUMBER(9) NOT NULL,
//  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO)
//);
public class LikesVO {
  /** 좋아요 번호 */
  private Integer likesno;
  
  /** 좋아요 */
  private Integer likes;
  
  /** 싫어요 */
  private Integer hates;
  
  /** 후기 번호 */
  private Integer reviewno;
}
