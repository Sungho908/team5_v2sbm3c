package dev.mvc.admin.member;

import java.net.http.HttpRequest;
import java.util.ArrayList;

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
  public ArrayList<MemberVO> list() {
    ArrayList<MemberVO> list = this.memberProc.list_all();
    return list;
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
