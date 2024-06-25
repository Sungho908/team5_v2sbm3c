package dev.mvc.shoes;

import java.util.ArrayList;
import java.util.Map;

import dev.mvc.option.OptionVO;
import dev.mvc.review.ReviewVO;

public interface ShoesDAOInter {
  /**
   * 등록 insert id="create" parameterType="dev.mvc.shoes.ShoesVO"
   * 
   * @param shoesVO
   * @return 등록 성공 여부
   */
  public int admin_create(ShoesVO shoesVO);
  
  /**
   * 수정 update id="update" parameterType="dev.mvc.shoes.ShoesVO"
   * 
   * @param shoesVO
   * @return 수정 성공 여부
   */
  public int admin_update(ShoesVO shoesVO);

  /**
   * 삭제 delete id="delete" parameterType="Integer"
   * 
   * @param Integer
   * @return 삭제 성공 여부
   */
  public int admin_delete(int shoesno);
  
  /**
   * 검색된 레코드 수 id="list_search_count" resultType="int" parameterType="Map"
   * @param map
   * @return 검색된 레코드 수
   */
  public int list_search_count(Map<String, Object> map);
  
  /**
   * 신발 목록 정보 id="list_search_paging" resultType="dev.mvc.shoes.ShoesVO" parameterType="Map"
   * @param map
   * @return 신발 목록 정보
   */
  public ArrayList<ShoesVO> list_search_paging(Map<String, Object> map);

  /**
   * 조회 id="read" resultMap="shoesRead" parameterType="Integer"
   * 
   * @param cateno
   * @return
   */
  public ShoesAllVO read(int shoesno);
  
  /**
   * 조회 id="read_option" resultMap="shoesOption" parameterType="Map"
   * 
   * @param cateno
   * @return
   */
  
  public ShoesAllVO read_option(Map<String, Object> map);
  
  public ShoesVO admin_read(int shoesno);
  
  public int admin_list_search_count(String word);
  
  public ArrayList<ShoesVO> admin_list_search_paging(Map<String, Object> map);
  
  /**
   * 전체 목록 select id="list_all" resultType="dev.mvc.shoes.ShoesVO"
   * 
   * @return 레코드 목록
   */
  public ArrayList<ShoesVO> admin_list_all();
  
  public int parent_count(int shoesno);
  
  // read했을때 옵션 리스트
  public ArrayList<OptionVO> option_paging(Map<String, Object> map);
  
  
  public int option_search_count(int shoesno);

  
  public int option_create(OptionVO optionVO);
  

  // 신발 옵션 수정(사이즈, 재고 등)
  public int option_update(OptionVO optionVO);
  
  public OptionVO shoes_option(Map<String, Object> map);
  
  
  public int option_delete(Map<String, Object> map);
  
  public ArrayList<ShoesVO> inquiry_select(String word);

  public ArrayList<ReviewVO> Shoes_reviews(int shoesno);
  
}