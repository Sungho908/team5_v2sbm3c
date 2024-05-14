package dev.mvc.member;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE MEMBER(
//    M_NO                              NUMBER(9)    NOT NULL    PRIMARY KEY,
//    M_ID                              VARCHAR2(100)    NOT NULL,
//    M_PW                              VARCHAR2(100)    NOT NULL,
//    M_NAME                            VARCHAR2(100)    NOT NULL,
//    M_NICKNAME                        VARCHAR2(100)    NOT NULL,
//    M_PHONE                           VARCHAR2(13)     NOT NULL,
//    M_EMAIL                           VARCHAR2(50)     NOT NULL,
//    M_THUMB                           VARCHAR2(100)    NULL ,
//    M_ADDR1                           VARCHAR2(100)    NULL ,
//    M_ADDR2                           VARCHAR2(100)    NULL ,
//    M_ZIPCODE                         NUMBER(5)    NULL ,
//    M_DATE                            DATE     NULL ,
//    M_RDATE                           DATE     NOT NULL,
//    M_POINT                           NUMBER(9)    DEFAULT 0     NULL ,
//    M_GENDER                          VARCHAR2(20)     NOT NULL,
//    M_GRADE                           NUMBER(2)    NULL ,
//    M_ROLE                            VARCHAR2(30)     DEFAULT USER    NOT NULL
//);
public class MemberVO {
  /** 멤버번호 */
  private Integer mNo;
  
  
  /** 멤버아이디 */
  private String mId;
  
  
  /** 멤버비밀번호 */
  private String mPw;
  
  
  /** 멤버 이름 */
  private String mName;
  
  
  /** 멤버 닉네임 */
  private String mNickname;
  
  
  /** 멤버 전화번호 */
  private String mPhone;
  
  
  /** 멤버 이메일 */
  private String mEmail;
  
  
  /** 멤버 섬네일 */
  private String mThumb = "";
  
  
  /** 멤버주소 */
  private String mAddr1 = "";
  
  
  /** 멤버 상세주소 */
  private String mAddr2 = "";
  
  
  /** 멤버 우편번호 */
  private Integer mZipcode = null;
  
  
  /** 멤버 생년월일 */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date mDate = null;
  
  
  /** 멤버 가입일 */
  private Date mRdate;
  
  
  /** 멤버 포인트 */
  private Integer mPoint;
  
  
  /** 멤버 성별 */
  private String mGender;
  
  
  /** 멤버 등급 */
  private Integer mGrade;
  
  
  /** 멤버 권한 */
  private MemberRole mRole;
  
  
  /** 썸네일파일 업로드를 위한 MultipartFile*/
  private MultipartFile mf;
}
