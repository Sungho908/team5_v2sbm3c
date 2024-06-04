package dev.mvc.shoes;

import java.util.ArrayList;

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

import dev.mvc.category.CategoryVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.option.OptionVO;
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

  private void table_paging_option(Model model, int shoesno, String word, int now_page) {
    ArrayList<ShoesOptionVO> list = this.shoesProc.option_paging(shoesno, word, now_page, this.record_per_page);
    model.addAttribute("list", list);

    int search_count = this.shoesProc.option_search_count(shoesno);
    System.out.println(search_count);
    String paging = this.shoesProc.pagingBox(now_page, word, "/shoes/read/" + shoesno, search_count,
        this.record_per_page, this.page_per_block); // 2, '',

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
  public String list_all(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
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
  public String create(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = new ShoesVO();
    model.addAttribute("shoesVO", shoesVO);

    ArrayList<ShoesVO> menu = this.shoesProc.list_all();
    model.addAttribute("menu", menu);

    table_paging(model, word, now_page);

    return "shoes/create";
  }

  /** 신발 생성 */
  @PostMapping(value = "/create")
  public String create_process(HttpSession session, Model model, @Valid ShoesVO shoesVO, BindingResult bindingResult,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    if (bindingResult.hasErrors()) {
      return "shoes/create";
    }

    int cnt = this.shoesProc.create(shoesVO);
    model.addAttribute("cnt", cnt);
    return "redirect:/shoes/list_all/" + shoesVO.getShoesno() + "?word=" + Tool.encode(word) + "&now_page=" + now_page;
  }

  /**
   * 옵션 생성 폼
   */
  @GetMapping(value = "/option_create/{shoesno}")
  public String option_create(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    // INSERT INTO OPTIONS (OPTIONNO, SIZES, COLOR, SHOESNO)
    // VALUES (OPTION_SEQ.nextval, #{sizes}, #{color})
    ShoesVO shoesVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    ShoesOptionVO shoesoptionVO = new ShoesOptionVO();
    shoesoptionVO.setShoesno(shoesno);
    model.addAttribute("shoesoptionVO", shoesoptionVO);

    table_paging(model, word, now_page);

    return "shoes/option_create";
  }

  /** 옵션 생성 */
  @PostMapping(value = "/option_create")
  public String option_create_process(HttpSession session, Model model, @Valid ShoesOptionVO shoesoptionVO,
      BindingResult bindingResult, @RequestParam(name = "shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    if (bindingResult.hasErrors()) {
      table_paging(model, word, now_page);
      return "shoes/option_create";
    }

    int cnt = this.shoesProc.option_create(shoesoptionVO);
    model.addAttribute("cnt", cnt);
    return "redirect:/shoes/read/" + shoesno + "?optionno=" + shoesoptionVO.getOptionno() + "&word=" + Tool.encode(word)
        + "&now_page=" + now_page;

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

    ShoesVO shoesVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    ArrayList<ShoesVO> menu = this.shoesProc.list_all();
    model.addAttribute("menu", menu);

    table_paging_option(model, shoesno, word, now_page);

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

  /** 옵션 수정 폼 */
  @GetMapping(value = "/option_update/{optionno}")
  public String option_update(HttpSession session, Model model, @PathVariable("optionno") Integer optionno,
      @RequestParam(name = "shoesno") Integer shoesno, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    ShoesOptionVO shoesoptionVO = this.shoesProc.read_option(shoesno, optionno);
    model.addAttribute("shoesoptionVO", shoesoptionVO);
    table_paging_option(model, shoesno, word, now_page);

    return "shoes/option_update";
  }

  /** 옵션 수정 */
  @PostMapping(value = "/option_update")
  public String option_update(HttpSession session, Model model, @Valid ShoesOptionVO shoesoptionVO,
      BindingResult bindingResult, @RequestParam(name = "shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    System.out.println("asdasd:" + shoesno);
    ShoesVO shoesVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    if (bindingResult.hasErrors()) {
      table_paging(model, word, now_page);
      return "shoes/update";
    }

    int cnt = this.shoesProc.option_update(shoesoptionVO);
    model.addAttribute("cnt", cnt);
    return "redirect:/shoes/read/" + shoesno + "?optionno=" + shoesoptionVO.getOptionno() + "&word=" + Tool.encode(word)
        + "&now_page=" + now_page;

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

  /** 옵션 삭제 폼 */
  @GetMapping(value = "/option_delete/{optionno}")
  public String option_delete(HttpSession session, Model model, @PathVariable("optionno") Integer optionno,
      @RequestParam(name = "shoesno") Integer shoesno, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesOptionVO shoesoptionVO = this.shoesProc.read_option(shoesno, optionno);
    model.addAttribute("shoesoptionVO", shoesoptionVO);

    ShoesVO shoesVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    table_paging(model, word, now_page);

    return "shoes/option_delete";

  }

  /** 옵션 삭제 */
  @PostMapping(value = "/option_delete")
  public String option_delete_process(HttpSession session, Model model, @Valid ShoesOptionVO shoesoptionVO,
      @RequestParam("shoesno") Integer shoesno, @RequestParam("optionno") Integer optionno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    System.out.println("optionno:" + optionno);
    ShoesVO shoesVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    int cnt = this.shoesProc.option_delete(shoesno, optionno);
    model.addAttribute("cnt", cnt);
    if (cnt == 1) {
      return "redirect:/shoes/list_all?now_page=1";
    } else {
      model.addAttribute("code", "delete_fail");

      return "shoes/msg";
    }
  }


  /**
   * 스니커즈 폼
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/sneakers")
  public String sneakers(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word) {

    ArrayList<ShoesVO> list = shoesProc.sneakers_list(word);
    model.addAttribute("list", list);
    System.out.println("list:" + list.size());
    return "shoes/sneakers"; // /templates/shoes/read.html
  }

  /**
   * 스니커즈 목록 리스트
   * 
   * @param session
   * @param model
   * @param categoryVO
   * @param word
   * @param now_page
   * @return
   */
  @PostMapping(value = "/sneakers_list")
  public String sneakers_list(HttpSession session, Model model,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "redirect:/shoes/sneakers";
  }

  /**
   * 슬립온 폼
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/slipon")
  public String slipon(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ArrayList<ShoesVO> list = shoesProc.slipon_list(word);
    model.addAttribute("list", list);
    
    return "shoes/slipon"; // /templates/shoes/read.html
  }

  /**
   * 슬립온 목록 리스트
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @PostMapping(value = "/slipon_list")
  public String slipon_list(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "redirect:/shoes/slipon";
  }
  
  
  /**
   * 부츠 목록
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/boots")
  public String boots(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/boots"; // /templates/shoes/read.html

  }

  /**
   * 워커 목록
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/worker")
  public String worker(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/worker"; // /templates/shoes/read.html

  }

  /**
   * 슬리퍼 목록
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/sandal")
  public String sandal(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/sandal"; // /templates/shoes/read.html

  }

  /**
   * 샌들 목록
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/slipper")
  public String slipper(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/slipper"; // /templates/shoes/read.html

  }

  /**
   * 러닝화 목록
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/running")
  public String running(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/running"; // /templates/shoes/read.html
  }

  /**
   * 로퍼 목록
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/roper")
  public String roper(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/roper"; // /templates/shoes/read.html
  }

  /**
   * 브랜드 목록
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/brand")
  public String brand(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/brand"; // /templates/shoes/read.html

  }

  /**
   * 나이키 목록
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/nike")
  public String nike(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/nike"; // /templates/shoes/read.html

  }

  /**
   * 뉴발란스 목록
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/nb")
  public String nb(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/nb"; // /templates/shoes/read.html

  }

  /**
   * 아디다스 목록
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/adidas")
  public String adidas(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/adidas"; // /templates/shoes/read.html

  }

  /**
   * 크록스 목록
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/crocs")
  public String crocs(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/crocs"; // /templates/shoes/read.html

  }

  /**
   * 컨버스 목록
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/converse")
  public String converse(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/converse"; // /templates/shoes/read.html

  }

  @GetMapping(value = "/showlist")
  public String showlist(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/showlist"; // /templates/shoes/read.html

  }
  /**
   * 장바구니 목록
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/basket")
  public String basket(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    table_paging(model, word, now_page);

    return "shoes/basket"; // /templates/shoes/read.html

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
  @GetMapping(value = "/product_details")
  public String product_details(HttpSession session, Model model,
      @RequestParam(name = "word", defaultValue = "") String word) {


    return "shoes/product_details"; // /templates/shoes/read.html

  }
  
  
  /**
   * 고객센터 상세
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/servicecenter")
  public String servicecenter(HttpSession session, Model model,
      @RequestParam(name = "word", defaultValue = "") String word) {


    return "shoes/servicecenter"; // /templates/shoes/read.html

  }
}
