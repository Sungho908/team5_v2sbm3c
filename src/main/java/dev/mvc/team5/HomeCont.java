package dev.mvc.team5;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
=======
>>>>>>> daec155c7e65840f2db5dab7588f3457314619f4
=======
>>>>>>> f70f4cf77208e3c096eb6034dca50f1fcb7273bf
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

<<<<<<< HEAD
<<<<<<< HEAD
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
    
    ArrayList<ShoesVOMenu> menu = this.shoesProc.menu();
    model.addAttribute("menu", menu);
    
    return "index"; // /templates/index.html  
  }
  
}






=======
=======
>>>>>>> f70f4cf77208e3c096eb6034dca50f1fcb7273bf
@Controller
public class HomeCont {

//  @Autowired
//  private Security security;

  public HomeCont() {
    System.out.println("-> HomeCont created.");
  }
  /*
   * @GetMapping(value = "/") // http://localhost:9093 public String home(Model
   * model) { // 파일명 return
   * 
   * System.out.println("-> http://localhost:9093");
   * 
   * return "/index"; // /templates/login.html }
   */

  @GetMapping(value = "/admin") // http://localhost:9091/admin
  public String admin(Model model) { // 파일명 return

    System.out.println("-> http://localhost:9093/admin");

    return "admin/index"; // /templates/index.html
  }
  
}
<<<<<<< HEAD
>>>>>>> daec155c7e65840f2db5dab7588f3457314619f4
=======
>>>>>>> f70f4cf77208e3c096eb6034dca50f1fcb7273bf
