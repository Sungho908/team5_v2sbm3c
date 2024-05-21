package dev.mvc.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.team5.DefaultCont;
import dev.mvc.tool.Alert;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  private PasswordEncoder pe;

  
  @GetMapping("signin")
  public String login(HttpServletRequest request, Model model,
                      @RequestParam(value = "error", required = false) String error) {
    if("disabled".equals(error)) {
      Alert message = new Alert("회원탈퇴처리된 회원입니다.", "/signin", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    
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
    return "login/signin";
  }

  @PostMapping("login")
  public String loginProc() {
    //Spring Security 에서 처리
    return "";
  }

  @GetMapping("signup")
  public String signUp(MemberVO memberVO) {
    return "login/signup";
  }

  @ResponseBody
  @GetMapping("checkId")
  public int checkId(@RequestParam("id") String id) {
    return this.memberProc.checkId(id);
  }

  @PostMapping("signup")
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
  
  @GetMapping("member/checkpw")
  public String checkpw() {
    return "member/checkpw";
  }
  
  @PostMapping("member/checkpw")
  public String checkpwProc(RedirectAttributes ra, HttpSession session,Model model,
                           @RequestParam("pw")String pw) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    memberVO = memberProc.readById(memberVO.getId());
    
    if(!pe.matches(pw, memberVO.getPw())) {
      Alert message = new Alert("틀린 비밀번호입니다.", "/member/checkpw", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    
    ra.addFlashAttribute("auth", "success");
    return "redirect:/member/update";
  }
  
  @GetMapping("member/update")
  public String update(HttpSession session, Model model,
                       @ModelAttribute("auth")String auth) {
    if(auth == null || auth != "success" ) {
      Alert message = new Alert("잘못된 접근입니다. 다시 접속하세요.", "/member/checkpw", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    memberVO = memberProc.readById(memberVO.getId());
    model.addAttribute("memberVO", memberVO);
    return "member/update";
  }
  
  @PostMapping("member/update")
  public String updateProc(MemberVO memberVO, Model model) {
      String file = "";
      String filename = "";
      String upDir = Tool.getUploadDir();
      MultipartFile mf = memberVO.getMf();

      // MultipartFile 객체가 null인지 확인
      if (mf != null && !mf.isEmpty()) {
          file = mf.getOriginalFilename();
      }

      // 비밀번호 암호화
      memberVO.setPw(pe.encode(memberVO.getPw()));
      System.out.println(memberVO.toString());

      // 파일 검사 및 저장
      if (!file.isEmpty() && !Tool.isImage(file)) {
          Alert message = new Alert("업로드가 불가능한 파일입니다. 이미지 파일을 등록해주세요.", "signup", RequestMethod.GET, null);
          return DefaultCont.showMessageAndRedirect(message, model);
      } else if (!file.isEmpty() && Tool.isImage(file)) {
          filename = Tool.saveFileSpring(mf, upDir);
          memberVO.setThumb(filename); // 새로운 파일이 저장된 경우에만 thumb 값을 설정
      }

      // 회원 생성 처리
      if (memberProc.update(memberVO) == 0) {
          Alert message = new Alert("알 수 없는 에러", "member/update", RequestMethod.GET, null);
          return DefaultCont.showMessageAndRedirect(message, model);
      }

      // 성공 메시지
      Alert message = new Alert("회원정보 수정 성공.", "/", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
  }

  @GetMapping("member/info")
  public String info() {
    return "member/info";
  }
  
  @GetMapping("member/delete")
  public String delete() {
    return "member/delete";
  }
  
  @PostMapping("member/delete")
  public String deleteProc(Model model, @RequestParam("pw")String pw, HttpSession session, RedirectAttributes ra) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    int memberno = memberVO.getMemberno();
    if(pe.matches(pw, memberVO.getPw())) {
      if(this.memberProc.deleteByMember(memberno) == 0) {
        Alert message = new Alert("알 수 없는 에러", "/member/delete", RequestMethod.GET, null);
        return DefaultCont.showMessageAndRedirect(message, model);
      }
    }else {
      ra.addFlashAttribute("code", 0);
      return "redirect:/member/delete";
    }
    session.invalidate();
    Alert message = new Alert("회원탈퇴처리가 완료되었습니다.", "/", RequestMethod.GET, null);
    return DefaultCont.showMessageAndRedirect(message, model);
  }
  
}
