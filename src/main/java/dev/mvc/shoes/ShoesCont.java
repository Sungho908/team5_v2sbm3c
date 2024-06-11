package dev.mvc.shoes;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.member.MemberProcInter;
import dev.mvc.option.OptionProcInter;
import dev.mvc.review.ReviewProcInter;
import dev.mvc.review.ReviewVO;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/shoes")
@Controller
public class ShoesCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;

  @Autowired
  @Qualifier("dev.mvc.option.OptionProc")
  private OptionProcInter optionProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") // @Service("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public ShoesCont() {
    System.out.println("-> ShoesCont created.");
  }

  private void table_paging(Model model, int categoryno, String word, int now_page) {
    
    ArrayList<ShoesVO> list = this.shoesProc.list_search_paging(categoryno, word);
    model.addAttribute("list", list);
    int search_count = this.shoesProc.list_search_count(categoryno, word);
    
    int no = search_count - ((now_page - 1) * this.record_per_page);

    model.addAttribute("now_page", now_page);
    model.addAttribute("categoryno", categoryno);
    model.addAttribute("word", word);
    model.addAttribute("no", no);
  }

  /**
   * 신발 리스트
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/list")
  public String list(HttpSession session, Model model,
      @RequestParam(name = "categoryno", defaultValue = "") int categoryno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    
    table_paging(model, categoryno, word, now_page);
    
    return "shoes/list";
  }
  
  /**
   * 제품 상세
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/{shoesno}")
  public String details(HttpSession session, Model model, 
      @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "categoryno") int categoryno) {
    System.out.println(session.getAttribute("ck_pw"));
    System.out.println(session.getAttribute("ck_save"));
    ShoesAllVO shoesAllVO = this.shoesProc.read(shoesno, categoryno);
    model.addAttribute("shoesAllVO", shoesAllVO);
    
    ArrayList<Integer> sizes = this.optionProc.option_sizes(shoesno, categoryno);
    model.addAttribute("sizes", sizes);
    
    ArrayList<String> color = this.optionProc.option_color(shoesno, categoryno);
    model.addAttribute("color", color);
    
    ArrayList<ShoesAllVO> review = this.reviewProc.review_list(shoesno);
    model.addAttribute("review", review);
    
    return "shoes/detail"; // /templates/shoes/read.html

  }

}
