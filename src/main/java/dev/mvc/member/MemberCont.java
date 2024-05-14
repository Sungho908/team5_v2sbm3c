package dev.mvc.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import dev.mvc.tool.Tool;

@Controller
public class MemberCont {
  @Autowired
  private MemberProcInter memberProc;
  
  
  @GetMapping("login")
  public String login(Model model) {
    return "login/login";
  }
  
  
  @GetMapping("signup")
  public String signUp(MemberVO memberVO) {
    return "login/signup";
  }
  
  @PostMapping("signup")
  
  public String signUpProc(MemberVO memberVO) {
    String file = "";
    String upDir = Tool.getUploadDir();
    System.out.println(memberVO.toString());
    MultipartFile mf = memberVO.getMf();
    file = mf.getOriginalFilename();
    
    if (file != "" && !Tool.checkUploadFile(file)) {// 업로드 가능한 파일인지 검사
      return "redirect:/signup";
    }
    Tool.saveFileSpring(memberVO.getMf(), upDir);
    
    int cnt = memberProc.create(memberVO);
    return "redirect:/";
  }
  
  
  @RequestMapping("member")
  public void memberUrl() {
    
  }
}
