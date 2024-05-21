package dev.mvc.noticeFile;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*CREATE TABLE NOTICE_FILE(
    NOTICE_FILE_NO                    NUMBER(9)    NOT NULL    PRIMARY KEY,
    NAME                              VARCHAR2(100)    NOT NULL,
    SIZES                             NUMBER     NOT NULL,
    EX                                VARCHAR2(100)    NOT NULL,
    SRC                               VARCHAR2(100)    NOT NULL,
    NOTICENO                          NUMBER(9)    NULL ,
  FOREIGN KEY (NOTICENO) REFERENCES NOTICE (NOTICENO)
);*/
@Getter@Setter
public class NoticeFileVO {
  
  /**
  이미지 파일
  <input type='file' class="form-control" name='file1MF' id='file1MF' 
             value='' placeholder="파일 선택">
  */
  private MultipartFile fileSelect;
  
  /** 파일번호 */
  private Integer notice_file_no;
  
  /** 파일명 */
  private String name;
  
  
  /** 파일크기 */
  private Long size;
  
  
  /** 확장자명 */
  private String ex;
  
  
  /** 파일 주소 */
  private String src;
  
  
  /** 후기 번호 */
  private Integer noticeno;
  
}
