package dev.mvc.shoes;

import java.util.ArrayList;
import java.util.Map;

public interface ShoesProcInter {
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
   * select id="read" resultType="dev.mvc.shoes.ShoesVO" parameterType="int"
   * @param shoesno
   * @return
   */
  public ShoesVO read(int shoesno);
  
  /**
   * 수정
   * update id="update" parameterType="dev.mvc.shoes.ShoesVO"    
   * @param shoesVO
   * @return 수정된 레코드 갯수
   */
  public int update(ShoesVO shoesVO);
  
  /**
   * delete
   * delete id="delete" parameterType="Integer"
   * @param shoesno
   * @return
   */
  public int delete(int shoesno);
  
  /**
   * 우선 순위 높임, 10 등 -> 1 등
   * update id="update_seqno_forward" parameterType="Integer"
   * @param shoesno
   * @return 수정한 레코드 갯수
   * */
  public int update_seqno_forward(int shoesno);
  
  /**
   * 우선 순위 낮춤, 1 등 -> 10 등
   * update id="update_seqno_backward" parameterType="Integer"
   * @param shoesno
   * @return 수정한 레코드 갯수
   * */
  public int update_seqno_backward(int shoesno);
 
  /**
   * 카테고리 공개 설정
   * update id="update_visible_y" parameterType="int"
   * @param shoesno
   * @return
   */
  public int update_visible_y(int shoesno);

  /**
   * 카테고리 비공개 설정
   * update id="update_visible_n" parameterType="int"
   * @param shoesno
   * @return
   */
  public int update_visible_n(int shoesno);
  
  /**
   * 회원/비회원에게 공개할 중분류 목록 
   * select id="list_all_y" resultType="dev.mvc.shoes.ShoesVO"     
   * @return
   */
  public ArrayList<ShoesVO> list_all_name_y();
  
  /**
   * 회원/비회원에게 공개할 소분류 목록 
   * select id="list_all_namesub_y" resultType="dev.mvc.shoes.ShoesVO" parameterType="String"
   * @return
   */
  public ArrayList<ShoesVO> list_all_namesub_y(String name);    
  
  /** 메뉴 */
  public ArrayList<ShoesVOMenu> menu();
  
  /**
   * 관리자용 검색 목록
   * select id="list_search" resultType="dev.mvc.shoes.ShoesVO" parameterType="String"
   * @param map
   * @return 조회한 레코드 목록
   */
  public ArrayList<ShoesVO> list_search(String word);      

  /**
   * 검색 + 페이징 목록
   * @param word 검색어
   * @param now_page 현재 페이지
   * @param record_per_page 페이지당 레코드수
   * @return
   */
  public ArrayList<ShoesVO> list_search_paging(String word, int now_page, int record_per_page);

  /**
   * 검색된 레코드 수
   * @param word
   * @return
   */
  public int list_search_count(String word);

  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param now_page  현재 페이지
   * @param word 검색어
   * @param list_file 목록 파일명
   * @param search_count 검색 레코드수   
   * @param record_per_page 페이지당 레코드 수
   * @param page_per_block 블럭당 페이지 수
   * @return 페이징 생성 문자열
   */ 
  public String pagingBox(int now_page, String word, String list_file, int search_count, 
                                      int record_per_page, int page_per_block);   
  
}







