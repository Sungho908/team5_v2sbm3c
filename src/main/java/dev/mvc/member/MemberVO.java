package dev.mvc.member;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.function.Supplier;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE MEMBER(
//    MEMBERNO                          NUMBER(9)    NOT NULL    PRIMARY KEY,
//    ID                                VARCHAR2(100)    UNIQUE       NOT NULL,
//    PW                                VARCHAR2(100)    NOT NULL,
//    NAME                              VARCHAR2(100)    NOT NULL,
//    NICKNAME                          VARCHAR2(100)    NOT NULL,
//    PHONE                             VARCHAR2(13)     NOT NULL,
//    EMAIL                             VARCHAR2(50)     NOT NULL,
//    THUMB                             VARCHAR2(100)    NULL ,
//    ADDR1                             VARCHAR2(100)    NULL ,
//    ADDR2                             VARCHAR2(100)    NULL ,
//    ZIPCODE                           NUMBER(5)    NULL ,
//    MDATE                             DATE     NULL ,
//    RDATE                             DATE     NOT NULL,
//    POINT                             NUMBER(9)    DEFAULT 0     NULL ,
//    GENDER                            VARCHAR2(20)     NOT NULL,
//    GRADE                             NUMBER(2)    NULL ,
//    ROLE                              VARCHAR2(30)     DEFAULT 'USER'    NOT NULL
//);
public class MemberVO {
  /** 멤버번호 */
  private Integer memberno;
  
  
  /** 멤버아이디 */
  private String id;
  
  
  /** 멤버비밀번호 */
  private String pw;
  
  
  /** 멤버 이름 */
  private String name;
  
  
  /** 멤버 닉네임 */
  private String nickname;
  
  
  /** 멤버 전화번호 */
  private String phone;
  
  
  /** 멤버 이메일 */
  private String email;
  
  
  /** 멤버 섬네일 */
  private String thumb = "";
  
  
  /** 멤버주소 */
  private String addr1 = "";
  
  
  /** 멤버 상세주소 */
  private String addr2 = "";
  
  
  /** 멤버 우편번호 */
  private Integer zipcode = 0;
  
  
  /** 멤버 생년월일 */
  @DateTimeFormat(pattern = "yyyy-M-d")
  private Date mdate = null;
  
  
  /** 멤버 가입일 */
  private Date rdate;
  
  
  /** 멤버 포인트 */
  private Integer point;
  
  
  /** 멤버 성별 */
  private String gender;
  
  
  /** 멤버 등급 */
  private Integer grade;
  
  
  /** 멤버 권한 */
  private MemberRole role;
  
  
  /** 썸네일파일 업로드를 위한 MultipartFile*/
  private MultipartFile mf;


  public MemberVO orElseThrow(Supplier<? extends Throwable> exceptionSupplier) throws Throwable {
    if (this == null) {
        throw exceptionSupplier.get();
    } else {
        return this;
    }
}
}