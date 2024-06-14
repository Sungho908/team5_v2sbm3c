package dev.mvc.shoes;

import java.util.ArrayList;

public interface ShoesProcInter {
  /**
   * 등록 insert id="create" parameterType="dev.mvc.shoes.ShoesVO"
   * 
   * @param shoesVO
   * @return 등록 성공 여부
   */
  public int create(ShoesVO shoesVO);

  /**
   * 수정 update id="update" parameterType="dev.mvc.shoes.ShoesVO"
   * 
   * @param shoesVO
   * @return 수정 성공 여부
   */
  public int update(ShoesVO shoesVO);

  /**
   * 삭제 delete id="delete" parameterType="Integer"
   * 
   * @param Integer
   * @return 삭제 성공 여부
   */
  public int delete(int shoesno);

  /**
   * 검색된 레코드 수 id="list_search_count" resultType="int" parameterType="Map"
   * 
   * @param map
   * @return 검색된 레코드 수
   */
  public int list_search_count(int categoryno, String word);

  /**
   * 신발 목록 정보 id="list_search_paging" resultType="dev.mvc.shoes.ShoesVO"
   * parameterType="Map"
   * 
   * @param map
   * @return 신발 목록 정보
   */
  public ArrayList<ShoesVO> list_search_paging(int categoryno, String word);
  
  public String pagingBox(int now_page, String word, String list_file, int search_count, 
      int record_per_page, int page_per_block);
  
  /**
   * 조회 id="read" resultMap="shoesRead" parameterType="Integer"
   * 
   * @param shoesno
   * @return ShoesAllVO
   */
  public ShoesAllVO read(int shoesno, int categoryno);
}
