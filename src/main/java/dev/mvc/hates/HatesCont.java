package dev.mvc.hates;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.review.ReviewProcInter;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/hates")
@Controller
public class HatesCont {

  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc;

  @Autowired
  @Qualifier("dev.mvc.hates.HatesProc")
  private HatesProcInter hatesProc;

  @PostMapping(value = "/hates")
  @ResponseBody
  public Map<String, Object> hates(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    // int memberno = session.getMemberno();
    Map<String, Object> response = new HashMap<>();

    int reviewno = Integer.parseInt(map.get("reviewno"));
    int memberno = 1;

    int cnt = 0;
    if (Boolean.parseBoolean(map.get("hated"))) { // decrease
      cnt = this.hatesProc.decreased_hates(reviewno, memberno);
      response.put("success", "decrease");
    } else {
      cnt = this.hatesProc.increased_hates(reviewno, memberno);
      response.put("success", "increase");
    }
    int hates_count = this.hatesProc.hates_count(reviewno);
    response.put("hates_count", hates_count);

    return response;
  }
}
