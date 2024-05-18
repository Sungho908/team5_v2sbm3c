package dev.mvc.category;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE CATEGORY(
//    C_NO                              NUMBER(9)    NOT NULL    PRIMARY KEY,
//    C_NAME                            VARCHAR2(100)    NOT NULL,
//    C_SUBNAME                         VARCHAR2(100)    NOT NULL,
//    C_SEQ                             NUMBER(9)    NULL ,
//    C_CNT                             NUMBER(9)    NOT NULL,
//    C_PARENT                          NUMBER(9)    NOT NULL,
//  FOREIGN KEY (C_PARENT) REFERENCES CATEGORY (C_NO)
//);
public class CategoryVO {
  /** 카테고리 번호 */
  private Integer cNo;
  
  
  /** 카테고리명 */
  private String cName;
  
  
  /** 카테고리서브명 */
  private String cSubname;
  
  
  /** 출력순서 */
  private Integer cSeq;
  
  
  /** 항목수 */
  private Integer cCnt;
  
  
  /** 카테고리 부모번호 */
  private Integer cParent;
}
