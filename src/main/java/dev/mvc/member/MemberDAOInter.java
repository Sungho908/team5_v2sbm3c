package dev.mvc.member;

public interface MemberDAOInter {
  /**
   * 멤버 생성
   * @param memberVO 객체
   * @return 성공한 쿼리 갯수
   * */
  public int create(MemberVO memberVO);
}
