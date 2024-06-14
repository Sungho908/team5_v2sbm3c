package dev.mvc.review;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mvc.shoes.ShoesAllVO;

@Service("dev.mvc.review.ReviewProc")
public class ReviewProc implements ReviewProcInter {
  @Autowired
  private ReviewDAOInter reviewDAO;

  @Override
  public ArrayList<ShoesAllVO> review_list(int shoesno) {
    ArrayList<ShoesAllVO> list = this.reviewDAO.review_list(shoesno);
    return list;
  }

  @Override
  public int create(ReviewVO reviewVO) {
    int cnt = this.reviewDAO.create(reviewVO);
    return cnt;
  }

  @Override
  public int update(ReviewVO reviewVO) {
    int cnt = this.reviewDAO.update(reviewVO);
    return cnt;
  }

  @Override
  public int delete(int reviewno) {
    int cnt = this.reviewDAO.delete(reviewno);
    return cnt;
  }

}
