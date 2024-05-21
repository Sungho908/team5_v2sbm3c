package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;  // class
import java.util.List;
// interface, 인터페이스를 사용하는 이유는 다른 형태의 구현 클래스로 변경시 소스 변경이 거의 발생 안됨
// 예) 2022년 세금 계산 방법 구현 class, 2023년 세금 계산 방법 구현 class
// 인터페이스 = 구현 클래스
// Payend pay = new Payend2022();
// Payend pay = new Payend2023();
// Payend pay = new Payend2024();
// pay.calc();
import java.util.Map;         

public interface MemberDAOInter {
  /**
   * 중복 아이디 검사
   * @param id
   * @return 중복 아이디 갯수
   */
  public int checkID(String id);
  
  /**
   * 회원 가입
   * @param memberVO
   * @return 추가한 레코드 갯수
=======
public interface MemberDAOInter {
  /**
   * 멤버 생성 <br>
   * id="create" parameterType="dev.mvc.member.MemberVO" <br>
   * 
   * @param memberVO 객체
   * @return 성공한 쿼리 갯수
>>>>>>> daec155c7e65840f2db5dab7588f3457314619f4
   */
  public int create(MemberVO memberVO);

  /**
<<<<<<< HEAD
   * 회원 전체 목록
   * @return
   */
  public ArrayList<MemberVO> list();

  /**
   * memberno로 회원 정보 조회
   * @param memberno
   * @return
   */
  public MemberVO read(int memberno);
  
  /**
   * id로 회원 정보 조회
   * @param id
   * @return
   */
  public MemberVO readById(String id);

  /**
   * 수정 처리
   * @param memberVO
   * @return
   */
  public int update(MemberVO memberVO);
 
  /**
   * 회원 삭제 처리
   * @param memberno
   * @return
   */
  public int delete(int memberno);
  
  /**
   * 현재 패스워드 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int passwd_check(HashMap<String, Object> map);
  
  /**
   * 패스워드 변경
   * @param map
   * @return 변경된 패스워드 갯수
   */
  public int passwd_update(HashMap<String, Object> map);
  
  /**
   * 로그인 처리
   */
  public int login(HashMap<String, Object> map);
  
 

   /* 중복 아이디 검사<br>
   * id="checkId" parameterType="String" resultType="int"<br>
   * 
   * @param 검색할 Id
   * @return 검색된 쿼리 갯수
   */
  public int checkId(String id);

  /**
   * Memberno로 회원 찾기<br>
   * id="readByMemberno" parameterType="int"
   * resultType="dev.mvc.member.MemberVO"<br>
   * 
   * @param 검색할 id명
   * @return MemberVO 객체
   */
  public MemberVO readByMemberno(int id);
  
  /**
   * 회원정보 수정<br>
   * id="update" parameterType="dev.mvc.member.MemberVO"
   * 
   * @param MemberVO 객체
   * @return 성공한 쿼리 갯수
   * */

}
