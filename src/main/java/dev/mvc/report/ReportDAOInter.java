package dev.mvc.report;

import java.util.ArrayList;
import java.util.Map;

public interface ReportDAOInter {
  /**
   * 멤버 생성 id="create" parameterType="dev.mvc.report.ReportVO"
   * 
   * @param ReportVO 객체
   * @return 성공한 쿼리 갯수
   */
  public int create(ReportVO reportVO);

  /**
   * 검색된 레코드 수 id="list_search_count" resultType="int" parameterType="String"
   * 
   * @param word 검색어
   * @return 성공한 쿼리 갯수
   */
  public int list_search_count(String word);

  /**
   * 페이징 id="list_search_paging" resultMap="InquiryResult" parameterType="Map"
   * 
   * @param Map
   * @return ArrayList<ReportInfoVO>
   */
  public ArrayList<ReportInfoVO> list_search_paging(Map<String, Object> map);

  /**
   * 기타 문의 상세 id="read" resultMap="InquiryResult" parameterType="Integer"
   * 
   * @param reportno
   * @return ReportInfoVO
   */
  public ReportInfoVO read(int reportno);

  /**
   * 답변 등록 id="answer" parameterType="Map"
   * 
   * @param Map<String, Object> map
   * @return int
   */
  public int answer(Map<String, Object> map);
}
