package dev.mvc.shoesFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE SHOES_FILE(
//    FILE_NO                           NUMBER(9)    NOT NULL    PRIMARY KEY,
//    FILE_NAME                         VARCHAR2(100)    NOT NULL,
//    FILE_SIZE                         NUMBER     NOT NULL,
//    FILE_EX                           VARCHAR2(100)    NOT NULL,
//    FILE_SRC                          VARCHAR2(100)    NOT NULL,
//    S_NO                              NUMBER(9)    NULL ,
//  FOREIGN KEY (S_NO) REFERENCES SHOSE (S_NO)
//);
public class ShoesFileVO {
  /** 신발 파일 번호 */
  private Integer fileNo;
  
  
  /** 파일명 */
  private String fileName;
  
  
  /** 파일크기 */
  private String fileSize;
  
  /** 확장자명 */
  private String fileEx;
  
  
  /** 파일주소 */
  private String fileSrc;
  
  
  /** 신발 번호 */
  private Integer sNo;
}
