package dev.mvc.shoes;

import java.util.ArrayList;
import java.util.Map;

import dev.mvc.admin.category.CategoryVO;
import dev.mvc.shoes.ShoesVO;

public interface ShoesDAOInter {
  /**
   * 등록
   * insert id="create" parameterType="dev.mvc.shoes.ShoesVO"
   * @param shoesVO
   * @return 등록한 레코드 갯수
   */
  public int create(ShoesVO shoesVO);
  
  
  
  public int option_create(ShoesOptionVO shoesoptionVO);
  /**
   * 전체 목록
   * select id="list_all" resultType="dev.mvc.shoes.ShoesVO"     
   * @return 레코드 목록
   */
  public ArrayList<ShoesVO> list_all();
  
  /**
   * 조회
   * select id="read" resultType="dev.mvc.cate.CateVO" parameterType="int"
   * @param cateno
   * @return
   */
  public ShoesVO read(int shoesno);
  
  public ShoesOptionVO read_option(Map<String, Object> map);
  
  // read했을때 옵션 리스트
  public ArrayList<ShoesOptionVO> option_paging(Map<String, Object> map);
   
  public ArrayList<ShoesVO> list_search(String word);
  
  public ArrayList<ShoesVO> list_search_paging(Map<String, Object> map);
    
  public int list_search_count(String word);
  
  public int update(ShoesVO shoesVO);
  
  // 신발 옵션 수정(사이즈, 재고 등)
  public int option_update(ShoesOptionVO shoesoptionVO);
  
  public int delete(int shoesno);
  
  public int parent_count(int shoesno);

}








