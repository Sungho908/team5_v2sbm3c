package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.likes.LikesProcInter;
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

  @Autowired
  @Qualifier("dev.mvc.likes.LikesProc")
  private LikesProcInter likesProc;

  public ReviewCont() {
    System.out.println("-> ShoesCont created.");
  }

  /* 후기 작성 */
  @PostMapping(value = "/create")
  @ResponseBody
  public Map<String, Object> create(HttpSession session, Model model, @RequestBody Map<String, String> map) {

    // session memberno 추가
    // int memberno = session.getMemberno();

    ReviewVO reviewVO = new ReviewVO();
    reviewVO.setContents(map.get("contents"));
    reviewVO.setGrade(Double.valueOf(map.get("grade")));
    reviewVO.setShoesno(Integer.parseInt(map.get("shoesno")));
    reviewVO.setMemberno(1);
    
    int cnt = this.reviewProc.create(reviewVO);
    cnt = cnt + this.likesProc.create();
    Map<String, Object> response = new HashMap<>();
    if (cnt == 2) {
      ArrayList<ShoesAllVO> review = this.reviewProc.review_list(Integer.parseInt(map.get("shoesno")));
      response.put("review", review);
      response.put("success", true);
    }
    return response;
  }

  /**
   * * 전체 후기
   * 
   * @param session
   * @param model
   * @param shoesno
   * @param categoryno
   * @return
   */
  @GetMapping(value = "/list/{shoesno}")
  public String list(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "categoryno") int categoryno) {

    return "review/list";
  }

  @PostMapping(value = "update")
  @ResponseBody
  public Map<String, Object> update(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    Map<String, Object> response = new HashMap<>();
    
    ReviewVO reviewVO = new ReviewVO();
    reviewVO.setContents(map.get("contents"));
    reviewVO.setGrade(Double.valueOf(map.get("grade")));
    reviewVO.setReviewno(Integer.parseInt(map.get("reviewno")));

    int cnt = this.reviewProc.update(reviewVO);
    if (cnt == 1) {
      response.put("success", true);
    }

    return response;
  }

  @PostMapping(value = "delete")
  @ResponseBody
  public Map<String, Object> delete(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    Map<String, Object> response = new HashMap<>();
    int reviewno = Integer.parseInt(map.get("reviewno"));
    System.out.println("review" + reviewno);

    this.likesProc.delete(reviewno);
    this.reviewProc.delete(reviewno);

    response.put("success", true);
    return response;
  }
}
