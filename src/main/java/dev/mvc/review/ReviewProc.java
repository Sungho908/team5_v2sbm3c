package dev.mvc.review;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mvc.basket.BasketVO;
import dev.mvc.shoes.ShoesAllVO;

@Service("dev.mvc.review.ReviewProc")
public class ReviewProc implements ReviewProcInter{
  @Autowired
  private ReviewDAOInter reviewDAO;

  @Override
  public ArrayList<ShoesAllVO> review_list(int shoesno) {
    ArrayList<ShoesAllVO> list = this.reviewDAO.review_list(shoesno);
    return list;
  }

  @Override
  public ArrayList<ReviewVO> review_list_all(int shoesno) {
    ArrayList<ReviewVO> list =this.reviewDAO.review_list_all(shoesno);
    return list;
  }

}
