package dev.mvc.admin.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin/member")
public class AdminMemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  
  @ModelAttribute("list")
  public ArrayList<MemberVO> list(@RequestParam(name = "now_page",required = false, defaultValue = "1")Integer now_page,
                                  @RequestParam(name="word",required = false, defaultValue = "")String word) {
    ArrayList<MemberVO> list = this.memberProc.list_search_paging(word, now_page, MemberVO.RECORD_PER_PAGE);
    
    return list;
  }
  
  @ModelAttribute("paging")
  public String paging(HttpServletRequest request,
      @RequestParam(name = "now_page",required = false, defaultValue = "1")Integer now_page,
      @RequestParam(name="word",required = false, defaultValue = "")String word,
      @RequestParam(name="key",required = false, defaultValue = "")String key,
      @RequestParam(name="memberno",required = false, defaultValue = "0")Integer memberno) {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("word", word);
    map.put("key",key);
    int search_count = this.memberProc.list_search_count(map);
    String path = request.getServletPath();
    String paging = this.memberProc.pagingBox(memberno, now_page, word, path, search_count);
    return paging;
  }
  
  
  @GetMapping("list")
  public String list(Model model) {
    return "admin/member/list";
  }
  
  @GetMapping("read")
  public String read(@RequestParam("memberno")Integer memberno, Model model) {
    MemberVO memberVO = this.memberProc.readByMemberno(memberno);
    model.addAttribute("memberVO", memberVO);
    return "admin/member/read";
  }
  
  @GetMapping("update")
  public String update(@RequestParam("memberno")Integer memberno, Model model) {
    MemberVO memberVO = this.memberProc.readByMemberno(memberno);
    model.addAttribute("memberVO", memberVO);
    return "admin/member/update";
  }
  
  @PostMapping("update")
  public String update(HttpServletRequest request, MemberVO memberVO) {
    System.out.println("updateProc:  " + memberVO.toString());
    this.memberProc.updateAdmin(memberVO);
    
    String referer = request.getHeader("Referer");
    return "redirect:" + referer;
  }
  
  @GetMapping("delete")
  public String delete(@RequestParam("memberno")Integer memberno, Model model) {
    MemberVO memberVO = this.memberProc.readByMemberno(memberno);
    model.addAttribute("memberVO", memberVO);
    return "admin/member/delete";
  }
  
  @PostMapping("delete")
  public String deleteProc(@RequestParam("memberno")Integer memberno, Model model) {
    if(this.memberProc.delete(memberno) != 0) {
      return "redirect:/admin/member/list";
    }
    return "redirect:/admin";
  }
}
