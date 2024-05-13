package dev.mvc.loginHistory;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE LOGIN_HISTORY(
//    LH_NO                             NUMBER(9)    NOT NULL    PRIMARY KEY,
//    LH_IP                             VARCHAR2(15)     NOT NULL,
//    LH_DATE                           DATE     NULL ,
//    M_NO                              NUMBER(9)    NOT NULL,
//  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
//);
public class LoginHistoryVO {
  /** 로그인내역 번호 */
  private Integer lhNo;
  
  
  /** 로그인내역 아이피 */
  private String lhIp;
  
  
  /** 로그인내역 접속일*/
  private Date lhDate;
  
  
  /** 멤버 번호 */
  private Integer mNo;
}
