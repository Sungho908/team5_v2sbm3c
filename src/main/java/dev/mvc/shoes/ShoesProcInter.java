package dev.mvc.shoes;

import java.util.ArrayList;

import dev.mvc.option.OptionVO;

public interface ShoesProcInter {
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

  
  public String pagingBox(int now_page, String word, String list_file, int search_count, int record_per_page,
      int page_per_block);

  /**
   * 조회 id="read" resultMap="shoesRead" parameterType="Integer"
   * 
   * @param shoesno
   * @return ShoesAllVO
   */
  
  public ShoesAllVO read(int shoesno);

  
  
  public ShoesVO admin_read(int shoesno);
  
  public int admin_list_search_count(String word);

  public ArrayList<ShoesVO> admin_list_search_paging(String word, int now_page, int record_per_page);
  
  
  
  /**
   * 전체 목록 select id="list_all" resultType="dev.mvc.shoes.ShoesVO"
   * 
   * @return 레코드 목록
   */
  public ArrayList<ShoesVO> admin_list_all();
  
  public int parent_count(int shoesno);
  
  
  // read했을때 옵션 리스트
  public ArrayList<OptionVO> option_paging(int shoesno, String word, int now_page, int record_per_page);
  
  
  public int option_search_count(int shoesno);
  
  public int option_create(OptionVO optionVO);
  
  // 신발 옵션 수정(사이즈, 재고 등)
  public int option_update(OptionVO optionVO);
  
  public OptionVO shoes_option(int optionno, int shoesno); // 단일 read
  
  public int option_delete(int shoesno, int optionno);
  
  public ArrayList<ShoesVO> inquiry_select(String word);
}
  
