package dev.mvc.loginHistory;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginHistoryCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  @Autowired
  @Qualifier("dev.mvc.loginHistory.LoginHistoryProc")
  private LoginHistoryProcInter lhProc;

  @GetMapping("history")
  public String history(HttpSession session, Model model) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    System.out.println(memberVO.toString());

    ArrayList<LoginHistoryVO> lhList = this.lhProc.readBymembernoRdateDesc(memberVO.getMemberno());
    model.addAttribute("list", lhList);

    return "login/history";
  }

}
