package dev.mvc.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.option.OptionVO;
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
  @GetMapping(value = "/basket_list/{memberno}")
  public String basket_list(HttpSession session, Model model, @PathVariable("memberno") Integer memberno) {

    ArrayList<ShoesAllVO> list = this.basketProc.list(memberno);
    model.addAttribute("list", list);

    return "basket/basket_list"; // /templates/basket/basket.html
  }

  @PostMapping(value = "/create")
  @ResponseBody
  public Map<String, Object> create(@RequestBody BasketVO basketVO, HttpSession session) {
    Map<String, Object> response = new HashMap<>();

    Integer memberno = 1; // 임시로 하드코딩

    // Integer memberno = (Integer) session.getAttribute("memberno");
    // if (memberno == null) {
    // response.put("message", "로그인이 필요합니다.");
    // }

    basketVO.setMemberno(memberno);

    int result = basketProc.create(basketVO.getMemberno(), basketVO.getColor(), basketVO.getSizes());
    if (result > 0) {
      response.put("success", true);
    } else {
      response.put("message", "장바구니에 추가하는데 실패했습니다.");
    }

    return response;
  }

  @PostMapping(value = "/update")
  @ResponseBody
  public Map<String, Object> update(@RequestBody Map<String, Object> map, HttpSession session) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    Map<String, Object> response = new HashMap<>();
    int memberno = memberVO.getMemberno();
    int basketno = (Integer) map.get("basketno");
    int amount = (Integer) map.get("amount");

    int result = basketProc.update(amount, memberno, basketno);

    if (result == 1) {
      response.put("success", true);
    } else {
      response.put("success", false);
      response.put("message", "수량 변경에 실패했습니다.");
    }

    return response;
  }

  @PostMapping(value = "/delete")
  @ResponseBody
  public Map<String, Object> delete(@RequestBody BasketVO basketVO, HttpSession session) {
    Map<String, Object> response = new HashMap<>();

    // 세션에서 멤버 번호 가져오기

    // Integer memberno = (Integer) session.getAttribute("memberno");
    // if (memberno == null) {
    // response.put("message", "세션이 만료되었거나 로그인 되어 있지 않습니다.");
    // return response;
    // }

    Integer memberno = 1;

    basketVO.setMemberno(memberno);

    // 장바구니 삭제 처리
    int result = basketProc.delete(basketVO.getMemberno(), basketVO.getBasketno());

    if (result > 0) {
      response.put("success", true);
    } else {
      response.put("message", "장바구니 제품 삭제에 실패했습니다.");
    }

    return response;
  }

  @PostMapping("/decrease")
  @ResponseBody
  public String decrease(@RequestBody OptionVO optionVO) {
    int result = basketProc.decrease(optionVO);
    if (result == 1) {
      return "Decreased successfully";
    } else {
      return "Failed to decrease";
    }
  }

  @PostMapping("/increase")
  @ResponseBody
  public String increase(@RequestBody OptionVO optionVO) {
    int result = basketProc.increase(optionVO);
    if (result == 1) {
      return "Increased successfully";
    } else {
      return "Failed to increase";
    }
  }

}
