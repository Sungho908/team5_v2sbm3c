package dev.mvc.admin.shoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.category.CategoryProcInter;
import dev.mvc.category.CategoryVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.option.OptionProcInter;
import dev.mvc.option.OptionVO;
import dev.mvc.shoes.ShoesProcInter;
import dev.mvc.shoes.ShoesVO;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/admin/shoes")
@Controller
public class AdminShoesCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;

  @Autowired
  @Qualifier("dev.mvc.option.OptionProc")
  private OptionProcInter optionProc;

  @Autowired
  @Qualifier("dev.mvc.category.CategoryProc")
  private CategoryProcInter categoryProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") // @Service("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public AdminShoesCont() {
    System.out.println("-> AdminShoesCont created.");
  }

  private void table_paging(Model model, String word, int now_page) {
    ArrayList<ShoesVO> list = this.shoesProc.admin_list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);

    int search_count = this.shoesProc.admin_list_search_count(word);
    String paging = this.shoesProc.pagingBox(now_page, word, "/admin/shoes/admin_list_all", search_count,
        this.record_per_page, this.page_per_block);

    int no = search_count - ((now_page - 1) * this.record_per_page);

    model.addAttribute("paging", paging);
    model.addAttribute("now_page", now_page);
    model.addAttribute("word", word);
    model.addAttribute("no", no);
  }

  private void table_paging_option(Model model, int shoesno, String word, int now_page) {
    ArrayList<OptionVO> list = this.shoesProc.option_paging(shoesno, word, now_page, this.record_per_page);
    model.addAttribute("list", list);

    int search_count = this.shoesProc.option_search_count(shoesno);
    System.out.println("search_count: " + search_count);

    String paging = this.shoesProc.pagingBox(now_page, word, "/admin/shoes/admin_read/" + shoesno, search_count,
        this.record_per_page, this.page_per_block); // 2, '',

    System.out.println("paging: " + paging);

    int no = search_count - ((now_page - 1) * this.record_per_page);

    System.out.println("no: " + no);

    model.addAttribute("paging", paging);
    model.addAttribute("now_page", now_page);
    model.addAttribute("word", word);
    model.addAttribute("no", no);
  }

  /**
   * 등록폼 + 목록
   * 
   * @param model
   * @param shoesVO
   * @return
   */
  @GetMapping(value = "/admin_list_all")
  public String admin_list_all(HttpSession session, Model model,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    word = Tool.checkNull(word).trim();
    // shoesVO.setNamesub("-"); // 폼 초기값 설정

    table_paging(model, word, now_page);

    return "admin/shoes/admin_list_all"; // /shoes/list_search.html
  }

  /**
   * 신발 생성 폼
   */
  @GetMapping(value = "/admin_create")
  public String admin_create(HttpSession session, Model model, 
      @RequestParam(name = "subname", defaultValue = "-") String subname,
      @RequestParam(name = "name", defaultValue = "") String name,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = new ShoesVO();
    model.addAttribute("shoesVO", shoesVO);

    ArrayList<ShoesVO> menu = this.shoesProc.admin_list_all();
    model.addAttribute("menu", menu);

    ArrayList<CategoryVO> name_list = this.categoryProc.select_name(subname);
    model.addAttribute("name_list", name_list);

    ArrayList<CategoryVO> subname_list = this.categoryProc.select_subname(name);
    model.addAttribute("subname_list", subname_list);

    table_paging(model, word, now_page);

    return "admin/shoes/admin_create";
  }

  /** 신발 생성 */
  @PostMapping(value = "/admin_create")
  public String create_process(HttpSession session, Model model, @Valid ShoesVO shoesVO, BindingResult bindingResult,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    if (bindingResult.hasErrors()) {
      return "admin/shoes/admin_create";
    }
    int cnt = this.shoesProc.admin_create(shoesVO);
    if (cnt == 1) {
      return "redirect:/admin/shoes/admin_list_all?now_page=1";
    } else {
      model.addAttribute("code", "create_fail");
      return "admin/shoes/msg";
    }
  }
  
  @PostMapping("/select_subname") 
  @ResponseBody
  public ArrayList<CategoryVO> select_subname(@RequestBody Map<String, Object> map) {
      String name = (String) map.get("name");
      
      // parentCategoryNo에 따라 소분류 목록을 가져오는 메서드 호출
      ArrayList<CategoryVO> subcategory = categoryProc.select_subname(name);
      return subcategory;
  }
  /**
   * 조회 + 목록
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/admin_read/{shoesno}")
  public String admin_read(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    System.out.println("Received now_page: " + now_page);

    ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    table_paging_option(model, shoesno, word, now_page);

    return "admin/shoes/admin_read"; //

  }

  /** 카테고리 수정 폼 */
  @GetMapping(value = "/admin_update/{shoesno}")
  public String admin_update(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);

    if (shoesVO.getTitle() == null) {
      model.addAttribute("code", "parent_update_fail");
      return "admin/shoes/msg";
    } else {

      model.addAttribute("shoesVO", shoesVO);
      table_paging(model, word, now_page);
      return "admin/shoes/admin_update";
    }

  }

  /** 카테고리 수정 */
  @PostMapping(value = "/admin_update")
  public String update_process(HttpSession session, Model model, @Valid ShoesVO shoesVO, BindingResult bindingResult,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    if (bindingResult.hasErrors()) {
      table_paging(model, word, now_page);
      return "admin/shoes/admin_update";
    }

    int cnt = this.shoesProc.admin_update(shoesVO);
    if (cnt == 1) {
      return "redirect:/admin/shoes/admin_read/" + shoesVO.getShoesno() + "?word=" + Tool.encode(word) + "&now_page="
          + now_page;
    } else {
      model.addAttribute("code", "update_fail");
      return "admin/shoes/msg";
    }
  }

  /** 카테고리 삭제 폼 */
  @GetMapping(value = "/admin_delete/{shoesno}")
  public String admin_delete(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    int parent_count = this.shoesProc.parent_count(shoesno);
    if (parent_count == 0) {
      ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);
      model.addAttribute("shoesVO", shoesVO);

      table_paging(model, word, now_page);

      return "admin/shoes/admin_delete";
    } else {
      model.addAttribute("code", "parent_delete_fail");
      return "admin/shoes/msg";
    }

  }

  /** 카테고리 삭제 */
  @PostMapping(value = "/admin_delete")
  public String admiin_delete_process(HttpSession session, Model model, @RequestParam("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    int cnt = this.shoesProc.admin_delete(shoesno);

    if (cnt == 1) {
      return "redirect:/admin/shoes/admin_list_all?now_page=1";
    } else {
      model.addAttribute("code", "delete_fail");
      return "admin/shoes/msg";
    }
  }

  /**
   * 옵션 생성 폼
   */
  @GetMapping(value = "/option_create/{shoesno}")
  public String option_create(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    OptionVO optionVO = new OptionVO();
    optionVO.setShoesno(shoesno);

    model.addAttribute("optionVO", optionVO);

    table_paging(model, word, now_page);

    return "admin/shoes/option_create";
  }

  /** 옵션 생성 */
  @PostMapping(value = "/option_create")
  public String option_create_process(HttpSession session, Model model, @Valid OptionVO optionVO,
      BindingResult bindingResult, @RequestParam(name = "shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    if (bindingResult.hasErrors()) {
      return "admin/shoes/option_create";
    }

    int cnt = this.shoesProc.option_create(optionVO);
    if (cnt == 1) {
      return "redirect:/admin/shoes/admin_list_all?now_page=1";
    } else {
      model.addAttribute("code", "create_fail");
      return "admin/shoes/msg";
    }

  }

  /** 옵션 수정 폼 */
  @GetMapping(value = "/option_update/{optionno}")
  public String option_update(HttpSession session, Model model, @PathVariable("optionno") Integer optionno,
      @RequestParam(name = "shoesno") Integer shoesno, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    OptionVO optionVO = this.shoesProc.shoes_option(optionno, shoesno);
    model.addAttribute("optionVO", optionVO);

    table_paging_option(model, shoesno, word, now_page);

    return "admin/shoes/option_update";
  }

  /** 옵션 수정 */
  @PostMapping(value = "/option_update")
  public String option_update(HttpSession session, Model model, @Valid OptionVO optionVO, BindingResult bindingResult,
      @RequestParam(name = "shoesno") Integer shoesno, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    if (bindingResult.hasErrors()) {
      table_paging(model, word, now_page);
      return "admin/shoes/option_update";
    }

    int cnt = this.shoesProc.option_update(optionVO);
    model.addAttribute("cnt", cnt);
    return "redirect:/admin/shoes/admin_read/" + shoesno + "?optionno=" + optionVO.getOptionno() + "&word="
        + Tool.encode(word) + "&now_page=" + now_page;

  }

  /** 옵션 삭제 폼 */
  @GetMapping(value = "/option_delete/{optionno}")
  public String option_delete(HttpSession session, Model model, @PathVariable("optionno") Integer optionno,
      @RequestParam(name = "shoesno") Integer shoesno, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    OptionVO optionVO = this.shoesProc.shoes_option(optionno, shoesno);
    model.addAttribute("optionVO", optionVO);

    ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    table_paging(model, word, now_page);

    return "admin/shoes/option_delete";

  }

  /** 옵션 삭제 */
  @PostMapping(value = "/option_delete")
  public String option_delete_process(HttpSession session, Model model, @Valid OptionVO optionVO,
      @RequestParam("shoesno") Integer shoesno, @RequestParam("optionno") Integer optionno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    System.out.println("optionno:" + optionno);
    ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    int cnt = this.shoesProc.option_delete(shoesno, optionno);
    model.addAttribute("cnt", cnt);
    if (cnt == 1) {
      return "redirect:/admin/shoes/admin_list_all?now_page=1";
    } else {
      model.addAttribute("code", "delete_fail");

      return "admin/shoes/msg";
    }
  }

}
