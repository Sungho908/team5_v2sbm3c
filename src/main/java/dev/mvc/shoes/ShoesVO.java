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
  private Integer sNo;
  
  
  /** 신발명 */
  private String sTitle;
  
  
  /** 신발 브랜드명 */
  private String sBrand;
  
  
  /** 신발 평점*/
  private Double sRating;
  
  
  /** 신발 가격*/
  private Double sPrice;
  
  
  /** 신발 할인율 */
  private Double sDiscount;
  
  
  /** 신발 설명 */
  private String sContents;
  
  
  /** 신발 판매 여부 */
  private char sVisible;
  
  
  /** 유저 번호 */
  private Integer mNo;
  
  
  /** 카테고리 번호 */
  private Integer cNo;
}
