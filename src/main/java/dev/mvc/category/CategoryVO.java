package dev.mvc.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/*
  CREATE TABLE CATEGORY(
    C_NO                              NUMBER(9)    NOT NULL    PRIMARY KEY,
    C_NAME                            VARCHAR2(100)    NOT NULL,
    C_SUBNAME                         VARCHAR2(100)    NOT NULL,
    C_SEQ                             NUMBER(9)    NULL ,
    C_CNT                             NUMBER(9)    NOT NULL,
    C_PARENT                          NUMBER(9),
  FOREIGN KEY (C_PARENT) REFERENCES CATEGORY (C_NO)
);
*/

@Setter @Getter
public class CategoryVO {
  /** 카테고리 번호 */
  private Integer categoryno = 0;
  
  /** 카테고리명 */
  @NotEmpty(message="카테고리명은 필수 입력 항목입니다.")
  @Size(min=1, max=10, message="카테고리명의 입력 글자 수는 최소 1자에서 10자 이어야 합니다.")
  private String name;
  
  /** 서브 카테고리명 */
  @NotEmpty(message="서브 카테고리명은 필수 입력 항목입니다.")
  @Size(min=1, max=30, message="서브 카테고리명의 입력 글자 수는 최소 1자에서 30자(한글 10자) 이어야합니다.")
  private String subname;
  
  /** 출력순서 */
  private Integer seq;
  
  /** 항목 수 */
  private Integer cnt;
  
  /** 카테고리 부모 번호 */
  private Integer parentno;
}
