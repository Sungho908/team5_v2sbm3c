package dev.mvc.member;

import java.util.ArrayList;

public interface MemberProcInter {
  /**
   * 멤버 생성 <br>
   * id="create" parameterType="dev.mvc.member.MemberVO" <br>
   * 
   * @param memberVO 객체
   * @return 성공한 쿼리 갯수
   */
  public int create(MemberVO memberVO);

  /**
   * 중복 아이디 검사<br>
   * id="checkId" parameterType="String" resultType="int"<br>
   * 
   * @param 검색할 Id
   * @return 검색된 쿼리 갯수
   */
  public int checkId(String id);
  
  /**
   * 아이디로 회원 찾기<br>
   * id="readById" parameterType="String" resultType="dev.mvc.member.MemberVO"<br>
   * 
   * @param 검색할 id명
   * @return MemberVO 객체
   * */
  public MemberVO readById(String id);
  
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
  public int update(MemberVO memberVO);
  
  /**
   * 회원이 탈퇴처리<br>
   * id="deleteByMember" parameterType="int"
   * 
   * @param memberno
   * @return 성공한 쿼리 갯수
   * */
  public int deleteByMember(int memberno);
  
  /**
   * 회원 목록 출력
   * id="list_all" resultType="dev.mvc.member.memberVO"
   * @return MemberVO 객체
   * */
  public ArrayList<MemberVO> list_all();
  
}