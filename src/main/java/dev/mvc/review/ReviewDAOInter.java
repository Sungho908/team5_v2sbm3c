package dev.mvc.review;

import java.util.ArrayList;

import dev.mvc.basket.BasketVO;
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
  

}
