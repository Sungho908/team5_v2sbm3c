package dev.mvc.shoes;

import java.util.ArrayList;

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
  
}







