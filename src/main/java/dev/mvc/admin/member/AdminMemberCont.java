package dev.mvc.admin.member;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;

@Controller
@RequestMapping("admin/member")
public class AdminMemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @GetMapping("list")
  public String list(Model model) {
    ArrayList<MemberVO> list = this.memberProc.list_all();
    model.addAttribute("list", list);
    return "admin/member/list";
  }
}
