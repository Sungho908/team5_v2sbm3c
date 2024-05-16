package dev.mvc.team5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCont {

//  @Autowired
//  private Security security;

  public HomeCont() {
    System.out.println("-> HomeCont created.");
  }

  @GetMapping(value = "/") // http://localhost:9093
  public String home(Model model) { // 파일명 return

    System.out.println("-> http://localhost:9093");

    return "admin/member/login"; // /templates/login.html
  }

  @GetMapping(value = "/admin") // http://localhost:9091/admin
  public String admin(Model model) { // 파일명 return

    System.out.println("-> http://localhost:9093/admin");

    return "admin/index"; // /templates/index.html
  }
  
}
