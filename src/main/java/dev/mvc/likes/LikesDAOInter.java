package dev.mvc.likes;

public interface LikesDAOInter {
  
  /**
   * likes 생성
   * insert id="create"
   * @return 생성 여부
   */
  public int create();
  
  /**
   * 좋아요 증가
   * update id="increased_likes" parameterType="Integer"
   * @param reviewno
   * @return 증가 여부
   */
  public int increased_likes(int reviewno);

  /**
   * 좋아요 감소
   * update id="decreased_likes" parameterType="Integer"
   * @param reviewno
   * @return 감소 여부
   */
  public int decreased_likes(int reviewno);
  
  /**
   * 싫어요 증가
   * update id="increased_hates" parameterType="Integer"
   * @param reviewno
   * @return 증가 여부
   */
  public int increased_hates(int reviewno);

  /**
   * 싫어요 감소
   * update id="decreased_hates" parameterType="Integer"
   * @param reviewno
   * @return 감소 여부
   */
  public int decreased_hates(int reviewno);
  
  /**
   * review의 likes 삭제 (review 삭제 전)
   * update id="delete" parameterType="Integer"
   * @param reviewno
   * @return
   */
  public int delete(int reviewno);

}
