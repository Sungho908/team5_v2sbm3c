package dev.mvc.shoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.mvc.admin.category.CategoryVO;
import dev.mvc.shoes.ShoesVO;

public interface ShoesDAOInter {
  /**
   * 등록 insert id="create" parameterType="dev.mvc.shoes.ShoesVO"
   * 
   * @param shoesVO
   * @return 등록한 레코드 갯수
   */
  public int create(ShoesVO shoesVO);

  public int option_create(ShoesOptionVO shoesoptionVO);

  /**
   * 전체 목록 select id="list_all" resultType="dev.mvc.shoes.ShoesVO"
   * 
   * @return 레코드 목록
   */
  public ArrayList<ShoesVO> list_all();
  
  // 스니커즈 목록 리스트
  public ArrayList<ShoesReviewVO> sneakers_list(int categoryno);

  // 슬립온 목록 리스트
  public ArrayList<ShoesReviewVO> slipon_list(String word);
  
  public ArrayList<ShoesReviewVO> boots_list(String word);
  
  public ArrayList<ShoesReviewVO> worker_list(String word);
  
  public ArrayList<ShoesReviewVO> review_list_all(int shoesno);
  

  /**
   * 조회 select id="read" resultType="dev.mvc.cate.CateVO" parameterType="int"
   * 
   * @param cateno
   * @return
   */

  
  public ShoesVO read(int shoesno);

  public ShoesOptionVO read_option(Map<String, Object> map);

  
  public ArrayList<ShoesReviewVO> review_paging(Map<String, Object> map);
  
  // read했을때 옵션 리스트
  public ArrayList<ShoesOptionVO> option_paging(Map<String, Object> map);

  public ArrayList<ShoesVO> list_search(String word);

  public ArrayList<ShoesVO> list_search_paging(Map<String, Object> map);

  public int list_search_count(String word);

  public int option_search_count(int shoesno);
  
  public int review_search_count(String word);
  

  public int update(ShoesVO shoesVO);

  // 신발 옵션 수정(사이즈, 재고 등)
  public int option_update(ShoesOptionVO shoesoptionVO);

  public int delete(int shoesno);

  public int option_delete(Map<String, Object> map);

  public int parent_count(int shoesno);

  public ArrayList<ShoesReviewVO> findByCategoryNo(Integer categoryno);
  public ArrayList<ShoesReviewVO> findByCategoryNoAndWord(Integer categoryno, String word);

}
