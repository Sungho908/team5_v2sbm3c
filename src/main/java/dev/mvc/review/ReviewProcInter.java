package dev.mvc.review;

import java.util.ArrayList;

import dev.mvc.shoes.ShoesAllVO;
import dev.mvc.shoes.ShoesVO;

public interface ReviewProcInter {

  public ArrayList<ShoesAllVO> review_list(int shoesno);

  public ArrayList<ReviewVO> review_list_all(int shoesno);

  public int create(ReviewVO reviewVO);

  public int update(ReviewVO reviewVO);

  public int delete(int reviewno);

  public String pagingBox(int now_page, String word, String list_file, int search_count, int record_per_page,
      int page_per_block);

  public int myReviewCount(int memberno);

  public ArrayList<ShoesAllVO> myReview(int memberno);

  public int list_search_count(String word);

  public ArrayList<ReviewVO> list_search_paging(String word, int now_page, int record_per_page);

  public ArrayList<ReviewVO> list();

  public ReviewVO read(int reviewno);

}
