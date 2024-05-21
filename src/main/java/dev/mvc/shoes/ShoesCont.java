package dev.mvc.shoes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.shoes.ShoesVO;
import dev.mvc.admin.category.CategoryVO;
import dev.mvc.member.MemberProc;
import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/shoes")
@Controller
public class ShoesCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") // @Service("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public ShoesCont() {
    System.out.println("-> ShoesCont created.");
  }

  private void table_paging(Model model, String word, int now_page) {
    ArrayList<ShoesVO> list = this.shoesProc.list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);

    int search_count = this.shoesProc.list_search_count(word);
    String paging = this.shoesProc.pagingBox(now_page, word, "/shoes/list_all", search_count, this.record_per_page,
        this.page_per_block);

    int no = search_count - ((now_page - 1) * this.record_per_page);

    model.addAttribute("paging", paging);
    model.addAttribute("now_page", now_page);
    model.addAttribute("word", word);
    model.addAttribute("no", no);
  }
//  @GetMapping(value="/create") // http://localhost:9091/shoes/create
//  @ResponseBody
//  public String create() {
//    return "<h2>Create test.</h2>";    
//  }

  /**
   * 등록폼 + 목록
   * 
   * @param model
   * @param shoesVO
   * @return
   */
  @GetMapping(value = "/list_all")
  public String list_all(HttpSession session, Model model, ShoesVO shoesVO,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    word = Tool.checkNull(word).trim();
    // shoesVO.setNamesub("-"); // 폼 초기값 설정

    table_paging(model, word, now_page);

    return "shoes/list_all"; // /shoes/list_search.html
  }

  /**
   * 신발 생성 폼
   */
  @GetMapping(value = "/create")
  public String create(HttpSession session, Model model) {

    ShoesVO shoesVO = new ShoesVO();
    model.addAttribute("shoesVO", shoesVO);
    return "shoes/create";
  }

  /** 카테고리 생성 */
  @PostMapping(value = "/create")
  public String create_process(HttpSession session, Model model, @Valid ShoesVO shoesVO, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "shoes/create";
    }

    int cnt = this.shoesProc.create(shoesVO);
    if (cnt == 1) {
      return "redirect:/shoes/create";
    } else {
      model.addAttribute("code", "create_fail");
      return "shoes/msg";
    }
  }

  /**
   * 조회 + 목록
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/read/{shoesno}")
  public String read(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ArrayList<ShoesVO> menu = this.shoesProc.list_all();
    model.addAttribute("menu", menu);

    ShoesVO shoesVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    table_paging(model, word, now_page);

    return "shoes/read"; // /templates/shoes/read.html

  }

  /** 카테고리 수정 폼 */
  @GetMapping(value = "/update/{shoesno}")
  public String update(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = this.shoesProc.read(shoesno);

    if (shoesVO.getTitle() == null) {
      model.addAttribute("code", "parent_update_fail");
      return "shoes/msg";
    } else {

      model.addAttribute("shoesVO", shoesVO);
      table_paging(model, word, now_page);
      return "shoes/update";
    }

  }

  /** 카테고리 수정 */
  @PostMapping(value = "/update")
  public String update_process(HttpSession session, Model model, @Valid ShoesVO shoesVO, BindingResult bindingResult,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    if (bindingResult.hasErrors()) {
      table_paging(model, word, now_page);
      return "shoes/update";
    }

    int cnt = this.shoesProc.update(shoesVO);
    if (cnt == 1) {
      return "redirect:/shoes/read/" + shoesVO.getShoesno() + "?word=" + Tool.encode(word) + "&now_page=" + now_page;
    } else {
      model.addAttribute("code", "update_fail");
      return "shoes/msg";
    }
  }

  /** 카테고리 삭제 폼 */
  @GetMapping(value = "/delete/{shoesno}")
  public String delete(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    
    int parent_count = this.shoesProc.parent_count(shoesno);
    
    if (parent_count == 0) {
      ShoesVO shoesVO = this.shoesProc.read(shoesno);
      model.addAttribute("shoesVO", shoesVO);

      table_paging(model, word, now_page);

      return "shoes/delete";
    } else {
      model.addAttribute("code", "parent_delete_fail");
      return "shoes/msg";
    }

  }

  /** 카테고리 삭제 */
  @PostMapping(value = "/delete")
  public String delete_process(HttpSession session, Model model, @RequestParam("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    int cnt = this.shoesProc.delete(shoesno);

    if (cnt == 1) {
      return "redirect:/shoes/list_all?now_page=1";
    } else {
      model.addAttribute("code", "delete_fail");
      return "shoes/msg";
    }
  }

}
