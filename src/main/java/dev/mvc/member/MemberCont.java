package dev.mvc.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dev.mvc.team5.DefaultCont;
import dev.mvc.tool.Alert;
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
  
  @ResponseBody
  @GetMapping("checkId")
  public int checkId(@RequestParam("id")String id) {
    return this.memberProc.checkId(id);
  }
  
  @PostMapping("signup")
  public String signUpProc(MemberVO memberVO, Model model) {
    String file = "";
    String filename = "";
    String upDir = Tool.getUploadDir();
    MultipartFile mf = memberVO.getMf();
    file = mf.getOriginalFilename();
    if (file != "" && !Tool.checkUploadFile(file)) {// 업로드 가능한 파일인지 검사
      return "redirect:/signup";
    }
    filename = Tool.saveFileSpring(memberVO.getMf(), upDir);
    memberVO.setMThumb(filename);
    
    
    if (memberProc.create(memberVO) == 0) {
      Alert message = new Alert("이미 존재하는 아이디입니다.", "signup", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    Alert message = new Alert("회원가입 성공.", "/", RequestMethod.GET, null);
    return DefaultCont.showMessageAndRedirect(message, model);
  }
  
  
  @RequestMapping("member")
  public void memberUrl() {
    
  }
}
