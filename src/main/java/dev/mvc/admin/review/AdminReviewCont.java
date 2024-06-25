package dev.mvc.admin.review;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.review.ReviewProcInter;
import dev.mvc.review.ReviewVO;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/admin/review")
@Controller
public class AdminReviewCont {
  
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  
  public AdminReviewCont() {
    System.out.println("-> AdminReviewCont created.");
  }
  
  
//  /** 리뷰 페이징 */
//  private void review_table_paging(Model model, String word, int now_page) {
//    ArrayList<ReviewVO> list = this.reviewProc.list_search_paging(word, now_page, this.record_per_page);
//
//    if (list.isEmpty()) {
//    } else {
//      model.addAttribute("list", list);
//
//      int search_count = this.reviewProc.list_search_count(word);
//      String paging = this.reviewProc.pagingBox(now_page, word, "/admin/review/list", search_count,
//          this.record_per_page, this.page_per_block);
//
//      int no = search_count - ((now_page - 1) * this.record_per_page);
//
//      model.addAttribute("paging", paging);
//      model.addAttribute("now_page", now_page);
//      model.addAttribute("word", word);
//      model.addAttribute("no", no);
//    }
//
//  }
  
  /** 리뷰 목록 */
  @GetMapping(value = "/list")
  public String list(HttpSession session, Model model,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    //word = Tool.checkNull(word).trim();
    
    ArrayList<ReviewVO> list  = this.reviewProc.list();
    model.addAttribute("list", list);
    //review_table_paging(model, word, now_page);

    return "admin/review/list";
  }
  
  
}
