package dev.mvc.member;

public interface MemberDAOInter {
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
  
}
