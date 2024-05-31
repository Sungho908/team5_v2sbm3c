package dev.mvc.admin.notice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/admin/notice")
@Controller
public class NoticeCont {

  @Autowired
  @Qualifier("dev.mvc.admin.notice.NoticeProc")
  private NoticeProcInter noticeProc;
  
  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;
  
  public NoticeCont() {
    System.out.println("-> Notice created.");
  }
  
  private void table_paging(Model model, String word, int now_page) {
    ArrayList<NoticeMemberVO> list = this.noticeProc.list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);
    int search_count = this.noticeProc.list_search_count(word);
    String paging = this.noticeProc.pagingBox(now_page, word, "/admin/category/list", search_count,
        this.record_per_page, this.page_per_block);

    int no = search_count - ((now_page - 1) * this.record_per_page);

    model.addAttribute("paging", paging);
    model.addAttribute("now_page", now_page);
    model.addAttribute("word", word);
    model.addAttribute("no", no);
  }
  
  @GetMapping(value = "list")
  public String list(HttpSession session, Model model, NoticeVO noticeVO,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    
    ArrayList<NoticeMemberVO> menu = this.noticeProc.list_all();
    model.addAttribute("menu", menu);

    
    table_paging(model, word, now_page);
    
    return "admin/notice/list";
  }
  
}
