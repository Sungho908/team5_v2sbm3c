package dev.mvc.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("member")
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  

  @Autowired
  private PasswordEncoder pe;

  @GetMapping("login")
  public String login(HttpServletRequest request, Model model) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("ck_id")) {
          model.addAttribute("ck_id", cookie.getValue());
        } else if (cookie.getName().equals("ck_pw")) {
          model.addAttribute("ck_pw", cookie.getValue());
        } else if (cookie.getName().equals("save")) {
          model.addAttribute("save", cookie.getValue());
        }
      }
    }
    return "member/login";
  }

  @PostMapping("login")
  public String loginProc() {
    return "";
  }

  @GetMapping("create")
  public String signUp(MemberVO memberVO) {
    return "member/create";
  }

  @ResponseBody
  @GetMapping("checkId")
  public int checkId(@RequestParam("id") String id) {
    return this.memberProc.checkId(id);
  }

  @PostMapping("create")
  public String signUpProc(MemberVO memberVO, Model model) {
    String file = "";
    String filename = "";
    String upDir = Tool.getUploadDir();
    MultipartFile mf = memberVO.getMf();
    file = mf.getOriginalFilename();

    memberVO.setPw(pe.encode(memberVO.getPw()));

    if (file != "" && !Tool.isImage(file)) {// 업로드 가능한 파일인지 검사
      Alert message = new Alert("업로드가 불가능한 파일입니다. 이미지 파일을 등록해주세요.", "signup", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    } else if (file != "" && Tool.isImage(file)) {
      filename = Tool.saveFileSpring(memberVO.getMf(), upDir);
      memberVO.setThumb(filename);
    }

    if (memberProc.create(memberVO) == 0) {
      Alert message = new Alert("이미 존재하는 아이디입니다.", "signup", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    Alert message = new Alert("회원가입 성공.", "/", RequestMethod.GET, null);
    return DefaultCont.showMessageAndRedirect(message, model);
  }
}
