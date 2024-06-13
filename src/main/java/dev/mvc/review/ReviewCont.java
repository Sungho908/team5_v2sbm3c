package dev.mvc.review;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.member.MemberProcInter;
import dev.mvc.option.OptionProcInter;
import dev.mvc.shoes.ShoesAllVO;
import dev.mvc.shoes.ShoesProcInter;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/review")
@Controller
public class ReviewCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;

  @Autowired
  @Qualifier("dev.mvc.option.OptionProc")
  private OptionProcInter optionProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc;

  public ReviewCont() {
    System.out.println("-> ShoesCont created.");
  }

  
  @GetMapping(value = "/list/{shoesno}")
  public String list(HttpSession session, Model model,
      @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "categoryno") int categoryno) {
    
    ArrayList<ReviewVO> list = this.reviewProc.review_list_all(shoesno);
    model.addAttribute("list", list);
    
    return "review/list";
  }

}
