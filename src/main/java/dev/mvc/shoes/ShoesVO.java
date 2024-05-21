package dev.mvc.shoes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE SHOES(
//    S_NO                              NUMBER(9)    NOT NULL    PRIMARY KEY,
//    C_NO                              NUMBER(9)    NOT NULL,
//    M_NO                              NUMBER(9)    NOT NULL,
//    S_TITLE                           VARCHAR2(100)    NOT NULL,
//    S_BRAND                           VARCHAR2(100)    NOT NULL,
//    S_RATING                          NUMBER     NOT NULL,
//    S_PRICE                           NUMBER     NOT NULL,
//    S_DISCOUNT                        NUMBER     NOT NULL,
//    S_CONTENTS                        VARCHAR2(1000)     NOT NULL,
//    S_VISIBLE                         CHAR(1)    NOT NULL,
//  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
//  FOREIGN KEY (C_NO) REFERENCES CATEGORY (C_NO)
//);
public class ShoesVO {
  /** 신발 번호 */
  private Integer shoesno;
  
  
  /** 신발명 */
  private String title;
  
  
  /** 신발 브랜드명 */
  private String brand;
  
  
  /** 신발 평점*/
  private Double rating;
  
  
  /** 신발 가격*/
  private Double price;
  
  
  /** 신발 할인율 */
  private Double discount;
  
  
  /** 신발 설명 */
  private String content;
  
  
  /** 신발 판매 여부 */
  private char visible;
  
  
  /** 유저 번호 */
  private Integer mNo;
  
  
  /** 카테고리 번호 */
  private Integer categoryno;
}
