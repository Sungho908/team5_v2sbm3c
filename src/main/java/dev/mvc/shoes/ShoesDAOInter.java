package dev.mvc.shoes;

import java.util.ArrayList;
import java.util.Map;

public interface ShoesDAOInter {
  /**
   * 등록
   * insert id="create" parameterType="dev.mvc.cate.ShoesVO"
   * @param cateVO
   * @return 등록한 레코드 갯수
   */
  public int create(ShoesVO cateVO);
  
  /**
   * 전체 목록
   * select id="list_all" resultType="dev.mvc.cate.ShoesVO"     
   * @return 레코드 목록
   */
  public ArrayList<ShoesVO> list_all();
  
  /**
   * 조회
   * select id="read" resultType="dev.mvc.cate.ShoesVO" parameterType="int"
   * @param cateno
   * @return
   */
  public ShoesVO read(int cateno);
  
  /**
   * 수정
   * update id="update" parameterType="dev.mvc.cate.ShoesVO"    
   * @param cateVO
   * @return 수정된 레코드 갯수
   */
  public int update(ShoesVO cateVO);
  
  /**
   * delete
   * delete id="delete" parameterType="Integer"
   * @param cateno
   * @return
   */
  public int delete(int cateno);

  /**
   * 우선 순위 높임, 10 등 -> 1 등
   * update id="update_seqno_forward" parameterType="Integer"
   * @param cateno
   * @return 수정한 레코드 갯수
   * */
  public int update_seqno_forward(int cateno);
  
  /**
   * 우선 순위 낮춤, 1 등 -> 10 등
   * update id="update_seqno_backward" parameterType="Integer"
   * @param cateno
   * @return 수정한 레코드 갯수
   * */
  public int update_seqno_backward(int cateno);
  
  /**
   * 카테고리 공개 설정
   * update id="update_visible_y" parameterType="int"
   * @param cateno
   * @return
   */
  public int update_visible_y(int cateno);

  /**
   * 카테고리 비공개 설정
   * update id="update_visible_n" parameterType="int"
   * @param cateno
   * @return
   */
  public int update_visible_n(int cateno);
    
  /**
   * 회원/비회원에게 공개할 중분류 목록 
   * select id="list_all_y" resultType="dev.mvc.cate.ShoesVO"     
   * @return
   */
  public ArrayList<ShoesVO> list_all_name_y();
  
  /**
   * 회원/비회원에게 공개할 소분류 목록 
   * select id="list_all_namesub_y" resultType="dev.mvc.cate.ShoesVO" parameterType="String"
   * @return
   */
  public ArrayList<ShoesVO> list_all_namesub_y(String name);    
  
  /**
   * 관리자용 검색 목록
   * select id="list_search" resultType="dev.mvc.cate.ShoesVO" parameterType="String"
   * @param map
   * @return 조회한 레코드 목록
   */
  public ArrayList<ShoesVO> list_search(String word);    
      
  /**
   * 검색목록 페이징
   * select id="list_search_paging" resultType="dev.mvc.cate.ShoesVO" parameterType="Map"
   * @param map
   * @return 조회한 레코드 목록
   */
  public ArrayList<ShoesVO> list_search_paging(Map<String, Object> map);
  
  /**
   * 검색된 레코드 수
   * @param word
   * @return
   */
  public int list_search_count(String word);
  
}








