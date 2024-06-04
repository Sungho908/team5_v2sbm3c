package dev.mvc.notice;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("notice")
public class NoticeCont {

  @Autowired
  @Qualifier("dev.mvc.notice.NoticeProc")
  private NoticeProcInter noticeProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 5개의 페이지로 구성됨 */
  public int page_per_block = 5;
  
  private void table_paging(Model model, String word, int now_page) {
    ArrayList<NoticeMemberVO> list = this.noticeProc.list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);
    if (list.isEmpty()) {
    } else {
      model.addAttribute("list", list);

      int search_count = this.noticeProc.list_search_count(word);
      String paging = this.noticeProc.pagingBox(now_page, word, "/admin/notice/list", search_count,
          this.record_per_page, this.page_per_block);

      int no = search_count - ((now_page - 1) * this.record_per_page);

      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      model.addAttribute("word", word);
      model.addAttribute("no", no);

    }

  }

  /** 공지 목록 */
  @GetMapping(value = "/list")
  public String notice(HttpSession session, Model model, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    word = Tool.checkNull(word).trim();
    
    table_paging(model, word, now_page);
    
    return "notice/list";
  }
  
  @PostMapping(value = "/increased_views")
  @ResponseBody
  public String increased_views(@RequestBody Integer noticeno) {
    System.out.println("번호: " + noticeno);
    return "성공";
  }
  
}
