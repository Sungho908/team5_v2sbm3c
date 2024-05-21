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
  
  public ArrayList<ShoesVO> list_search(String word);
  
  public ArrayList<ShoesVO> list_search_paging(Map<String, Object> map);
    
  public int list_search_count(String word);
  
  public int update(ShoesVO shoesVO);
  
  public int delete(int shoesno);
  
  public int parent_count(int shoesno);
  
  
  
}








