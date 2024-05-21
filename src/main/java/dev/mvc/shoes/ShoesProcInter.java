package dev.mvc.shoes;

import java.util.ArrayList;
import java.util.Map;

import dev.mvc.admin.category.CategoryVO;
import dev.mvc.shoes.ShoesVO;
import dev.mvc.shoes.ShoesVOMenu;

public interface ShoesProcInter {
  /**
   * 등록 insert id="create" parameterType="dev.mvc.shoes.ShoesVO"
   * 
   * @param shoesVO
   * @return 등록한 레코드 갯수
   */
  public int create(ShoesVO shoesVO);

  /**
   * 전체 목록 select id="list_all" resultType="dev.mvc.shoes.ShoesVO"
   * 
   * @return 레코드 목록
   */
  public ArrayList<ShoesVO> list_all();

//  /**
//   * 조회 select id="read" resultType="dev.mvc.shoes.ShoesVO" parameterType="int"
//   * 
//   * @param shoesno
//   * @return
//   */
//  public ShoesVO read(int shoesno);

//  /**
//   * 수정 update id="update" parameterType="dev.mvc.shoes.ShoesVO"
//   * 
//   * @param shoesVO
//   * @return 수정된 레코드 갯수
//   */
//  public int update(ShoesVO shoesVO);

  /**
   * 조회
   * select id="read" resultType="dev.mvc.cate.CateVO" parameterType="int"
   * @param cateno
   * @return
   */
  public ShoesVO read(int shoesno);
  
  public ArrayList<ShoesVO> list_search(String word);

  public ArrayList<ShoesVO> list_search_paging(String word, int now_page, int record_per_page);

  public String pagingBox(int now_page, String word, String list_file, int search_count, int record_per_page,
      int page_per_block);

  public int list_search_count(String word);
  
  public int update(ShoesVO shoesVO);

  public int delete(int shoesno);

  public int parent_count(int shoesno);
}
