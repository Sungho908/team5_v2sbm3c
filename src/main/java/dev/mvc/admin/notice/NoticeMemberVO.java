package dev.mvc.admin.notice;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

/** notice, member join VO*/
@Setter @Getter
public class NoticeMemberVO {
  
  private Integer noticeno;
  
  private String title;
  
  private String contents;
  
  private Date rdate;
  
  private Integer views;

  private String nickname;
}
