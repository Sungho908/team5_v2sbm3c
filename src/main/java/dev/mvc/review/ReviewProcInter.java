package dev.mvc.review;

import java.util.ArrayList;

import dev.mvc.shoes.ShoesAllVO;

public interface ReviewProcInter {

  public ArrayList<ShoesAllVO> review_list(int shoesno);
}
