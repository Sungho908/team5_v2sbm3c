package dev.mvc.report;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE REPORT(
//    REPORT_NO                         NUMBER(9)    NOT NULL    PRIMARY KEY,
//    REPORT_COMMENT                    VARCHAR2(1000)     NOT NULL,
//    REPORT_TYPE_NO                    NUMBER(9)    NOT NULL,
//    M_NO                              NUMBER(9)    NOT NULL,
//    R_NO                              NUMBER(9)    NOT NULL,
//  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
//  FOREIGN KEY (R_NO) REFERENCES REVIEW (R_NO),
//  FOREIGN KEY (REPORT_TYPE_NO) REFERENCES REPORT_TYPE (REPORT_TYPE_NO)
//);
public class ReportVO {
  /** 신고번호 */
  private Integer reportNo;
  
  
  /** 신고내용 */
  private String reportComment;
  
  
  /** 신고유형번호 */
  private Integer reportTypeNo;
  
  
  /** 멤버 번호 */
  private Integer mNo;
  
  
  /** 후기 번호 */
  private Integer rNo;
}
