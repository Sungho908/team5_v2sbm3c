package dev.mvc.inquiry;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.member.MemberVO;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/inquiry")
@Controller
public class inquiryCont {

  @PostMapping(value = "/select")
  @ResponseBody
  public Map<String, Object> select(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    Map<String, Object> response = new HashMap<>();
    String select = map.get("select");
    String word = map.get("word");

    switch (select) {
    case "shoes":
      // 쿼리 실행
      break;
    case "payment":
      // 쿼리 실행
      break;
    case "other":
      // 쿼리 실행
      break;
    default:
      break;
    }

    System.out.println("select" + select);
    System.out.println("text" + text);
    response.put("success", true);
    return response;
  }
}
