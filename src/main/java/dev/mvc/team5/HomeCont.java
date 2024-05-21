package dev.mvc.team5;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.mvc.shoes.ShoesVO;
import dev.mvc.shoes.ShoesVOMenu;
import dev.mvc.shoes.ShoesProcInter;

@Controller
public class HomeCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;
  
//  @Autowired
//  private Security security;
  
  public HomeCont() {
    System.out.println("-> HomeCont created.");
  }
  
  @GetMapping(value="/") // http://localhost:9091
  public String home(Model model) { // 파일명 return
//    if (this.security != null) {
//      System.out.println("-> 객체 고유 코드: " + security.hashCode());
//      System.out.println(security.aesEncode("1234"));
//    }
    return "index"; // /templates/index.html  
  }
  
}





