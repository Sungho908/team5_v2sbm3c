package dev.mvc.option;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE OPTION(
//    O_NO                              NUMBER(9)    NULL      PRIMARY KEY,
//    O_SIZE                            NUMBER(4)    NOT NULL,
//    O_AMOUNT                          NUMBER(9)    NOT NULL,
//    O_COLOR                           VARCHAR2(30)     NOT NULL,
//    S_NO                              NUMBER(9)    NOT NULL,
//  FOREIGN KEY (S_NO) REFERENCES SHOSE (S_NO)
//);
public class OptionVO {
  /** 옵션 번호 */
  private Integer optionno;
  
  
  /** 신발 사이즈 */
  private Integer sizes;
  
  
  /** 신발 재고 */
  private Integer amount;
  
  
  /** 신발 색상 */
  private Integer color;
  
  
  /** 신발 번호 */
  private Integer shoesno;
}
