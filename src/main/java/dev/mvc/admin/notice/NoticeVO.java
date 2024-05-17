package dev.mvc.admin.notice;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/*
CREATE TABLE NOTICE(
    N_NO                              NUMBER(9)    NOT NULL    PRIMARY KEY,
    N_TITLE                             VARCHAR2(100)       NOT NULL,
    N_COMMENT                         VARCHAR2(1000)     NOT NULL,
    N_DATE                            DATE     NOT NULL,
    N_FILE                            VARCHAR2(100)    NOT NULL,
    N_SIZE                            NUMBER(9)    NOT NULL,
    M_NO                              NUMBER(9)    NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);
*/
@Setter @Getter
public class NoticeVO {
  
  /** 공지사항 번호 */
  private Integer n_no= 0;

  /** 공지사항 제목 */
  @NotEmpty(message="중분류명은 필수 입력 항목입니다.")
  @Size(min=1, max=30, message="중분류명의 입력 글자 수는 최소 1자에서 30자 이어야 합니다.")
  private String n_title;
  
  /** 공지사항 내용 */
  @NotEmpty(message="중분류명은 필수 입력 항목입니다.")
  @Size(min=1, message="중분류명의 입력 글자 수는 최소 1자 이상 이어야 합니다.")
  private String n_comment;
  
  /** 공지사항 작성일 */
  private Integer n_date;
  
  /** 첨부 파일 */
  private Integer n_file;
  
  /** 첨부 파일 크기 */
  private Integer n_size;
  
  /** 작성자 번호 */
  private Integer m_no;
  
}
