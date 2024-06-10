package dev.mvc.basket;

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
import dev.mvc.shoes.ShoesAllVO;
import dev.mvc.shoes.ShoesProcInter;
import dev.mvc.shoes.ShoesVO;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/basket")
@Controller
public class BasketCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;

  @Autowired
  @Qualifier("dev.mvc.basket.BasketProc")
  private BasketProcInter basketProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") // @Service("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public BasketCont() {
    System.out.println("-> BasketCont created.");
  }

  private void table_paging(Model model, int memberno, String word, int now_page) {

    ArrayList<ShoesVO> list = this.shoesProc.list_search_paging(memberno, word);
    model.addAttribute("list", list);
    int search_count = this.shoesProc.list_search_count(memberno, word);

    int no = search_count - ((now_page - 1) * this.record_per_page);

    model.addAttribute("now_page", now_page);
    model.addAttribute("memberno", memberno);
    model.addAttribute("word", word);
    model.addAttribute("no", no);
  }

  /**
   * 장바구니
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/basket/{memberno}")
  public String basket(HttpSession session, Model model, 
      @PathVariable("basketno") Integer basketno,
      @RequestParam(name = "memberno") int memberno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    
    ArrayList<ShoesAllVO> list = this.basketProc.getBasket(basketno, memberno);
    model.addAttribute("list", list);
    
    table_paging(model, memberno, word, now_page);
    return "basket/basket"; // /templates/basket/basket.html
  }

}
