package dev.mvc.review;

import java.util.ArrayList;

import dev.mvc.shoes.ShoesAllVO;

public interface ReviewDAOInter {

  /**
   * 리뷰 id = "review_list" resultType="dev.mvc.review.ReviewVO"
   * 
   * @param integer
   * @return 리뷰 데이터
   */
  public ArrayList<ShoesAllVO> review_list(int shoesno);

  public ArrayList<ReviewVO> review_list_all(int shoesno);

  /**
   * 후기 생성 id="create" parameterType="dev.mvc.review.ReviewVO"
   * 
   * @param reviewVO
   * @return 생성 여부
   */
  public int create(ReviewVO reviewVO);

  /**
   * 후기 수정 update id="update" parameterType="dev.mvc.review.ReviewVO"
   * 
   * @param ReviewVO
   * @return 수정 여부
   */
  public int update(ReviewVO reviewVO);

  /**
   * 후기 생성 id="delete" parameterType="Integer"
   * 
   * @param reviewno
   * @return 삭제 여부
   */
  public int delete(int reviewno);

  /**
   * 작성한 댓글 내역 select id="myReview" resultMap="myReview" parameterType="Int"
   * 
   * @param memberno
   * @return ArrayList<ShoesAllVO>
   */
  public ArrayList<ShoesAllVO> myReview(int memberno);
}