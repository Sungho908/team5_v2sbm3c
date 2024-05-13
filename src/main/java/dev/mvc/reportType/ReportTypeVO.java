package dev.mvc.reportType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE REPORT_TYPE(
//    REPORT_TYPE_NO                    NUMBER(9)    NOT NULL    PRIMARY KEY,
//    REPORT_TYPE                       VARCHAR2(100)    NOT NULL
//);
public class ReportTypeVO {
  /** 신고유형번호 */
  private Integer reportTypeNo;
  
  
  /** 신고 유형*/
  private String reportType;
}
