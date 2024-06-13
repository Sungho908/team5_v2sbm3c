package dev.mvc.review;

import java.util.ArrayList;

import dev.mvc.basket.BasketVO;
import dev.mvc.shoes.ShoesAllVO;

public interface ReviewProcInter {

  public ArrayList<ShoesAllVO> review_list(int shoesno);
  
  
  public ArrayList<ReviewVO> review_list_all(int shoesno);

}
