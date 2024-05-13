package dev.mvc.noticeFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE NOTICE_FILE(
//    FILE_NO                           NUMBER(9)    NOT NULL    PRIMARY KEY,
//    FILE_NAME                         VARCHAR2(100)    NOT NULL,
//    FILE_SIZE                         NUMBER     NOT NULL,
//    FILE_EX                           VARCHAR2(100)    NOT NULL,
//    FILE_SRC                          VARCHAR2(100)    NOT NULL,
//    R_NO                              VARCHAR2(255)    NULL ,
//    N_NO                              NUMBER(9)    NULL ,
//  FOREIGN KEY (N_NO) REFERENCES NOTICE (N_NO)
//);
public class NoticeFileVO {
  /** 파일번호 */
  private Integer fileNo;
  
  
  /** 파일명 */
  private String fileName;
  
  
  /** 파일크기 */
  private Double fileSize;
  
  
  /** 확장자명 */
  private String fileEx;
  
  
  /** 파일 주소 */
  private String fileSrc;
  
  
  /** 후기 번호 */
  private Integer rNo;
  
  
  /** 공지번호 */
  private Integer nNo;
}
