package dev.mvc.loginHistory;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;

public interface LoginHistoryProcInter {
  /**
   * 로그인 IP정보 기록<br>
   * id="create" parameterType="dev.mvc.loginHistory.LoginHistoryVO"<br>
   * 
   * @param LoginHistory 객체
   * @return 성공한 쿼리 갯수
   */
  public int create(int memberno, HttpServletRequest request);

  /**
   * member에 저장된 로그인정보 갯수<br>
   * id="countByMemberno" parameterType="int" resultType="int"<br>
   * 
   * @param memberno
   * @return 저장된 쿼리 갯수
   */
  public int countByMemberno(int memberno);
  
  /**
   * 회원 로그인정보 기록일 최근순으로 정렬<br>
   * id="readBymembernoRdateDesc" parameterType="int" resultType="dev.mvc.loginHistory.LoginHistoryVO"
   * 
   * @param memberno
   * @return LoginHistoryVO 객체
   * */
  public ArrayList<LoginHistoryVO> readBymembernoRdateDesc(int memberno);
  
  /**
   * 최근10건뺴고 날짜가 오래된것 삭제
   * id="deleteOldRdateForMemberno" parameterType="int"
   * @param memberno
   * @return 성공한 쿼리 갯수
   * */
  public int deleteOldRdateForMemberno(int memberno);
}
