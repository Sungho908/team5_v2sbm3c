package dev.mvc.team5;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

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
  

}





