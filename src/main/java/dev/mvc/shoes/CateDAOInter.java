package dev.mvc.shoes;

import java.util.ArrayList;

public interface CateDAOInter {
  /**
   * 등록
   * insert id="create" parameterType="dev.mvc.cate.CateVO"
   * @param cateVO
   * @return 등록한 레코드 갯수
   */
  public int create(CateVO cateVO);
  
  /**
   * 전체 목록
   * select id="list_all" resultType="dev.mvc.cate.CateVO"     
   * @return 레코드 목록
   */
  public ArrayList<CateVO> list_all();
  
  /**
   * 조회
   * select id="read" resultType="dev.mvc.cate.CateVO" parameterType="int"
   * @param cateno
   * @return
   */
  public CateVO read(int cateno);
  
  /**
   * 수정
   * update id="update" parameterType="dev.mvc.cate.CateVO"    
   * @param cateVO
   * @return 수정된 레코드 갯수
   */
  public int update(CateVO cateVO);
  
  /**
   * delete
   * delete id="delete" parameterType="Integer"
   * @param cateno
   * @return
   */
  public int delete(int cateno);
  
}








