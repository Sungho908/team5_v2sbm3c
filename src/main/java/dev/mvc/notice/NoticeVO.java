package dev.mvc.notice;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE NOTICE(
//    N_NO                              NUMBER(9)    NOT NULL    PRIMARY KEY,
//    N_COMMENT                         VARCHAR2(1000)     NOT NULL,
//    N_DATE                            DATE     NOT NULL,
//    M_NO                              NUMBER(9)    NOT NULL,
//  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
//);
public class NoticeVO {
  /** 공지사항 번호 */
  private Integer nNo;
  
  
  /** 공지사항 내용 */
  private String nComment;
  
  
  /** 공지사항 작성일 */
  private Date nDate;
  
  
  /** 멤버 번호 */
  private Integer mNo;
}
