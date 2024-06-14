package dev.mvc.review;

import java.util.ArrayList;

import dev.mvc.shoes.ShoesAllVO;

public interface ReviewProcInter {

  public ArrayList<ShoesAllVO> review_list(int shoesno);
  
  public int create(ReviewVO reviewVO);

  public int update(ReviewVO reviewVO);
  
  public int delete(int reviewno);
}
