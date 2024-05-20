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

import dev.mvc.member.MemberProc;
import dev.mvc.member.MemberProcInter;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/shoes")
@Controller
public class ShoesCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")  // @Service("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 10;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 10;
  
  public ShoesCont() {
    System.out.println("-> ShoesCont created.");  
  }
  
//  @GetMapping(value="/create") // http://localhost:9091/shoes/create
//  @ResponseBody
//  public String create() {
//    return "<h2>Create test.</h2>";    
//  }

  // create 폼 출력
//  @GetMapping(value="/create") // http://localhost:9091/shoes/create
//  public String create(Model model, ShoesVO shoesVO) {
//    
//    ArrayList<ShoesVO> list = this.shoesProc.list_search();
//    model.addAttribute("list", list);
//    
//    return "shoes/create"; // /templates/shoes/create.html
//  }
  
  /**
   * create 폼 데이터 처리
   * http://localhost:9091/shoes/list_search
   * @param model
   * @param shoesVO
   * @param bindingResult
   * @return
   */
  @PostMapping(value="/create") // http://localhost:9091/shoes/create
  public String create(Model model, @Valid ShoesVO shoesVO, BindingResult bindingResult, 
      @RequestParam(name="word", defaultValue="") String word,
      @RequestParam(name="now_page", defaultValue="1") int now_page) {
    
    ArrayList<ShoesVOMenu> menu = this.shoesProc.menu();
    model.addAttribute("menu", menu);
    
    if (bindingResult.hasErrors()) {
      // 페이징 목록
      ArrayList<ShoesVO> list = this.shoesProc.list_search_paging(word, now_page, this.record_per_page);    
      model.addAttribute("list", list);
      
      // 페이징 버튼 목록
      int search_count = this.shoesProc.list_search_count(word);
      
      // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
      int no = search_count - ((now_page - 1) * this.record_per_page);
      model.addAttribute("no", no);
      
      String paging = this.shoesProc.pagingBox(now_page, 
          word, "shoes/list_search", search_count, this.record_per_page, this.page_per_block);
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      
      return "shoes/list_search";  // /templates/shoes/list_search.html
    }
    
    int cnt = this.shoesProc.create(shoesVO);
    System.out.println("-> cnt: " + cnt);

    model.addAttribute("cnt", cnt);
    if (cnt == 1) { // 등록 성공
//      model.addAttribute("code", "create_success");
//      model.addAttribute("name", shoesVO.getName());
//      model.addAttribute("namesub", shoesVO.getNamesub());
      
      return "redirect:/shoes/list_search"; // /shoes/list_search.html
      
    } else { // 실패
      model.addAttribute("code", "create_fail");
      return "shoes/msg"; // /templates/shoes/msg.html
    }
  }
  
//  /**
//   * 등록폼 + 목록
//   * @param model
//   * @param shoesVO
//   * @return
//   */
//  @GetMapping(value="/list_search")
//  public String list_all(Model model, ShoesVO shoesVO) {
//    // shoesVO.setNamesub("-"); // 폼 초기값 설정
//    
//    ArrayList<ShoesVOMenu> menu = this.shoesProc.menu();
//    model.addAttribute("menu", menu);
//    
//    ArrayList<ShoesVO> list = this.shoesProc.list_search();
//    model.addAttribute("list", list);
//    
//    return "shoes/list_search"; // /shoes/list_search.html
//  }

  /**
   * 조회 + 목록
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value="/read/{shoesno}")
  public String read(Model model, 
                            @PathVariable("shoesno") Integer shoesno, 
                            @RequestParam(name="word", defaultValue = "") String word,
                            @RequestParam(name="now_page", defaultValue="1") int now_page) {
    
    // word = Tool.checkNull(word);
    
    ArrayList<ShoesVOMenu> menu = this.shoesProc.menu();
    model.addAttribute("menu", menu);
    
    ShoesVO shoesVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesVO", shoesVO);
    
    // 페이징 목록
    ArrayList<ShoesVO> list = this.shoesProc.list_search_paging(word, now_page, this.record_per_page);    
    model.addAttribute("list", list);
    
    // 페이징 버튼 목록
    int search_count = this.shoesProc.list_search_count(word);
    String paging = this.shoesProc.pagingBox(now_page, 
        word, "/shoes/list_search", search_count, this.record_per_page, this.page_per_block);
    model.addAttribute("paging", paging);
    model.addAttribute("now_page", now_page);
    
    model.addAttribute("word", word);
    
    // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
    int no = search_count - ((now_page - 1) * this.record_per_page);
    model.addAttribute("no", no);
    
    return "shoes/read";  // /templates/shoes/read.html
    
  }
  
  /**
   * 수정폼
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value="/update/{shoesno}")
  public String update(HttpSession session,
                                Model model, 
                                @PathVariable("shoesno") Integer shoesno, 
                                @RequestParam(name="word", defaultValue = "") String word,
                                @RequestParam(name="now_page", defaultValue = "1") int now_page) {
    
    if (this.memberProc.isMemberAdmin(session)) {
      ArrayList<ShoesVOMenu> menu = this.shoesProc.menu();
      model.addAttribute("menu", menu);
      
      ShoesVO shoesVO = this.shoesProc.read(shoesno);
      model.addAttribute("shoesVO", shoesVO);
      
      // 페이징 목록
      ArrayList<ShoesVO> list = this.shoesProc.list_search_paging(word, now_page, this.record_per_page);    
      model.addAttribute("list", list);
      
      // 페이징 버튼 목록
      int search_count = this.shoesProc.list_search_count(word);
      String paging = this.shoesProc.pagingBox(now_page, 
          word, "/shoes/list_search", search_count, this.record_per_page, this.page_per_block);
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      
      model.addAttribute("word", word);
      
      // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
      int no = search_count - ((now_page - 1) * this.record_per_page);
      model.addAttribute("no", no);
      
      return "shoes/update";  // /templates/shoes/update.html

    } else {
      return "redirect:/member/login_form_need";  // redirect
    }
    
    
  }
  
  /**
   * 수정 처리
   * @param model
   * @param shoesVO
   * @param bindingResult
   * @return
   */
  @PostMapping(value="/update") // http://localhost:9091/shoes/update
  public String update_process(HttpSession session,
                                            Model model, 
                                            @Valid ShoesVO shoesVO, BindingResult bindingResult, 
                                            @RequestParam(name="word", defaultValue = "") String word,
                                            @RequestParam(name="now_page", defaultValue="1") int now_page) {
    if (this.memberProc.isMemberAdmin(session)) {
      ArrayList<ShoesVOMenu> menu = this.shoesProc.menu();
      model.addAttribute("menu", menu);
      
      if (bindingResult.hasErrors()) {
        // 페이징 목록
        ArrayList<ShoesVO> list = this.shoesProc.list_search_paging(word, now_page, this.record_per_page);    
        model.addAttribute("list", list);
        
        // 페이징 버튼 목록
        int search_count = this.shoesProc.list_search_count(word);
        String paging = this.shoesProc.pagingBox(now_page, 
            word, "shoes/list_search", search_count, this.record_per_page, this.page_per_block);
        model.addAttribute("paging", paging);
        model.addAttribute("now_page", now_page);
        
        return "shoes/update";  // /templates/shoes/update.html
      }
      
      int cnt = this.shoesProc.update(shoesVO);
//      System.out.println("-> cnt: " + cnt);

      model.addAttribute("cnt", cnt);
      if (cnt == 1) {
        return "redirect:/shoes/update/" + shoesVO.getSNo() + "?word=";// + Tool.encode(word) + "&now_page=" + now_page;
        
      } else {
        model.addAttribute("code", "update_fail");
        return "shoes/msg"; // /templates/shoes/msg.html
      }
    } else {
      return "redirect:/member/login_form_need";  // redirect
    }
  }
  
  /**
   * Delete form
   * http://localhost:9091/shoes/delete/1
   * @param model
   * @param shoesno Shoesgory number to delete.
   * @return
   */
  @GetMapping(value="/delete/{shoesno}")
  public String delete(HttpSession session, Model model, 
                               @PathVariable("shoesno") Integer shoesno, 
                               @RequestParam(name="word", defaultValue = "") String word,
                               @RequestParam(name="now_page", defaultValue = "1") int now_page) {
    
    if (this.memberProc.isMemberAdmin(session)) {
      ArrayList<ShoesVOMenu> menu = this.shoesProc.menu();
      model.addAttribute("menu", menu);
      
      ShoesVO shoesVO = this.shoesProc.read(shoesno);
      model.addAttribute("shoesVO", shoesVO);
      
      // 페이징 목록
      ArrayList<ShoesVO> list = this.shoesProc.list_search_paging(word, now_page, this.record_per_page);    
      model.addAttribute("list", list);
      
      // 페이징 버튼 목록
      int search_count = this.shoesProc.list_search_count(word);
      String paging = this.shoesProc.pagingBox(now_page, 
          word, "/shoes/list_search", search_count, this.record_per_page, this.page_per_block);
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      
      model.addAttribute("word", word);
      
      // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
      int no = search_count - ((now_page - 1) * this.record_per_page);
      model.addAttribute("no", no);
      
      return "shoes/delete";  // /templates/shoes/delete.html
    } else {
      return "redirect:/member/login_form_need";  // redirect
    }
    
  }
  
  /**
   * Delete process
   * @param model
   * @param shoesno 삭제할 레코드 번호
   * @param bindingResult
   * @return
   */
  @PostMapping(value="/delete")
  public String delete_process(Model model, Integer shoesno, 
                                           @RequestParam(name="word", defaultValue = "") String word,
                                           @RequestParam(name="now_page", defaultValue = "1") int now_page) {
    int cnt = this.shoesProc.delete(shoesno); // 삭제
    // System.out.println("-> cnt: " + cnt);
    
    model.addAttribute("cnt", cnt);
    
    // ----------------------------------------------------------------------------------------------------------
    // 마지막 페이지에서 모든 레코드가 삭제되면 페이지수를 1 감소 시켜야함.
    int search_cnt = this.shoesProc.list_search_count(word);
    if (search_cnt % this.record_per_page == 0) {
      now_page = now_page - 1;
      if (now_page < 1) {
        now_page = 1; // 최소 시작 페이지
      }
    }
    // ----------------------------------------------------------------------------------------------------------
    
    if (cnt == 1) {
      return "redirect:/shoes/list_search?word=" ;//+ Tool.encode(word) + "&now_page=" + now_page;
    } else {
      model.addAttribute("code", "delete_fail");
      return "shoes/msg"; // /templates/shoes/msg.html
    }
  }

  /**
   * 출력 순서 높임: seqno 10 -> 1
   * http://localhost:9091/shoes/update_seqno_forward/1
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value="/update_seqno_forward/{shoesno}")
  public String update_seqno_forward(Model model, 
                                                       @PathVariable("shoesno") Integer shoesno, 
                                                       @RequestParam(name="word", defaultValue = "") String word,
                                                       @RequestParam(name="now_page", defaultValue = "1") int now_page) {

    this.shoesProc.update_seqno_forward(shoesno);
    
    return "redirect:/shoes/list_search?word=" ;//+ Tool.encode(word) + "&now_page=" + now_page;  // /templates/shoes/list_search.html
    
  }

  /**
   * 출력 순서 낮춤: seqno 1 -> 10
   * http://localhost:9091/shoes/update_seqno_backward/1
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value="/update_seqno_backward/{shoesno}")
  public String update_seqno_backward(Model model, 
                                                         @PathVariable("shoesno") Integer shoesno, 
                                                         @RequestParam(name="word", defaultValue = "") String word,
                                                         @RequestParam(name="now_page", defaultValue = "1") int now_page) {
   
    this.shoesProc.update_seqno_backward(shoesno);
    
    return "redirect:/shoes/list_search?word=" ;//+ Tool.encode(word) + "&now_page=" + now_page;  // /templates/shoes/list_search.html
    
  }
  
  /**
   * 카테고리 공개 설정
   * http://localhost:9091/shoes/update_visible_y/1
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value="/update_visible_y/{shoesno}")
  public String update_visible_y(Model model, 
                                             @PathVariable("shoesno") Integer shoesno, 
                                             @RequestParam(name="word", defaultValue = "") String word) {

    this.shoesProc.update_visible_y(shoesno);
    
    return "redirect:/shoes/list_search?word=" ;//+ Tool.encode(word);  // /templates/shoes/list_search.html
  }
  
  /**
   * 카테고리 비공개 설정
   * http://localhost:9091/shoes/update_visible_n/1
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value="/update_visible_n/{shoesno}")
  public String update_visible_n(Model model, 
                                             @PathVariable("shoesno") Integer shoesno, 
                                             @RequestParam(name="word", defaultValue = "") String word) {
    this.shoesProc.update_visible_n(shoesno);
    
    return "redirect:/shoes/list_search?word=" ;//+ Tool.encode(word);  // /templates/shoes/list_search.html
  }

//  /**
//   * 등록폼 + 검색 목록
//   * http://localhost:9091/shoes/list_search?word=개발  ← GET Form
//   * http://localhost:9091/shoes/list_search/개발  ← @PathVariable
//   * @param model
//   * @param shoesVO
//   * @return
//   */
//  @GetMapping(value="/list_search")
//  public String list_search(Model model, ShoesVO shoesVO, String word) {
//    // shoesVO.setNamesub("-"); // 폼 초기값 설정
//    word = Tool.checkNull(word).trim();    
//    System.out.println("--> word: " + word);
//    
//    ArrayList<ShoesVOMenu> menu = this.shoesProc.menu();
//    model.addAttribute("menu", menu);
//    
//    ArrayList<ShoesVO> list = this.shoesProc.list_search(word);
//    model.addAttribute("list", list);
//    
//    model.addAttribute("word", word);
//    
//    return "shoes/list_search"; // /shoes/list_search.html
//  }

  /**
   * 등록폼 + 검색 목록
   * http://localhost:9091/shoes/list_search?word=개발  ← GET Form
   * http://localhost:9091/shoes/list_search/개발  ← @PathVariable
   * @param model
   * @param shoesVO
   * @param word 검색어
   * @param now_page 현재 페이지 번호
   * @return
   */
  @GetMapping(value="/list_search")
  public String list_search(HttpSession session, Model model, ShoesVO shoesVO, 
                                     String word, 
                                     @RequestParam(name="now_page", defaultValue="1") int now_page) {
    if (this.memberProc.isMemberAdmin(session)) {
      // shoesVO.setNamesub("-"); // 폼 초기값 설정
      //word = Tool.checkNull(word).trim();
      // System.out.println("--> word: " + word);
      
      ArrayList<ShoesVOMenu> menu = this.shoesProc.menu();
      model.addAttribute("menu", menu);
      
      // 페이징 목록
      ArrayList<ShoesVO> list = this.shoesProc.list_search_paging(word, now_page, this.record_per_page);    
      model.addAttribute("list", list);
      
      // 페이징 버튼 목록
      int search_count = this.shoesProc.list_search_count(word);
      
      String paging = this.shoesProc.pagingBox(now_page, 
          word, "/shoes/list_search", search_count, this.record_per_page, this.page_per_block);
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      
      model.addAttribute("word", word);
      
      // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
      int no = search_count - ((now_page - 1) * this.record_per_page);
      model.addAttribute("no", no);
      
      return "shoes/list_search"; // /shoes/list_search.html
    } else {
      return "redirect:/member/login_form_need";  // redirect
    }
    
  }  
}



