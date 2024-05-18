package dev.mvc.member;

<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MemberVO {
  /*
  memberno NUMBER(10) NOT NULL, -- 회원 번호, 레코드를 구분하는 컬럼 
  id         VARCHAR(30)   NOT NULL UNIQUE, -- 이메일(아이디), 중복 안됨, 레코드를 구분 
  passwd     VARCHAR(60)   NOT NULL, -- 패스워드, 영숫자 조합
  mname      VARCHAR(30)   NOT NULL, -- 성명, 한글 10자 저장 가능
  tel            VARCHAR(14)   NOT NULL, -- 전화번호
  zipcode     VARCHAR(5)        NULL, -- 우편번호, 12345
  address1    VARCHAR(80)       NULL, -- 주소 1
  address2    VARCHAR(50)       NULL, -- 주소 2
  mdate       DATE             NOT NULL, -- 가입일    
  grade        NUMBER(2)     NOT NULL, -- 등급(1~10: 관리자, 11~20: 회원, 40~49: 정지 회원, 99: 탈퇴 회원)
  */

    /** 회원 번호 */
    private int memberno;
    /** 아이디(이메일) */
    private String id = "";
    /** 패스워드 */
    private String passwd = "";
    /** 회원 성명 */
    private String mname = "";
    /** 전화 번호 */
    private String tel = "";
    /** 우편 번호 */
    private String zipcode = "";
    /** 주소 1 */
    private String address1 = "";
    /** 주소 2 */
    private String address2 = "";
    /** 가입일 */
    private String mdate = "";
    /** 등급 */
    private int grade = 0;

    /** 등록된 패스워드 */
    private String old_passwd = "";
    /** id 저장 여부 */
    private String id_save = "";
    /** passwd 저장 여부 */
    private String passwd_save = "";
    /** 이동할 주소 저장 */
    private String url_address = "";
    
    
}


=======
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
  private Integer mZipcode = 0;
  
  
  /** 멤버 생년월일 */
  @DateTimeFormat(pattern = "yyyy-M-d")
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


  public MemberVO orElseThrow(Supplier<? extends Throwable> exceptionSupplier) throws Throwable {
    if (this == null) {
        throw exceptionSupplier.get();
    } else {
        return this;
    }
}
}
>>>>>>> daec155c7e65840f2db5dab7588f3457314619f4
