package dev.mvc.likes;

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

@RequestMapping("/likes")
@Controller
public class LikesCont {

  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc;

  @Autowired
  @Qualifier("dev.mvc.likes.LikesProc")
  private LikesProcInter likesProc;

  @PostMapping(value = "/likes")
  @ResponseBody
  public Map<String, Object> likes(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    // int memberno = session.getMemberno();
    Map<String, Object> response = new HashMap<>();

    int reviewno = Integer.parseInt(map.get("reviewno"));
    int memberno = 1;

    int cnt = 0;
    if (Boolean.parseBoolean(map.get("liked"))) { // decrease
      cnt = this.likesProc.decreased_likes(reviewno, memberno);
      response.put("success", "decrease");
    } else {
      cnt = this.likesProc.increased_likes(reviewno, memberno);
      response.put("success", "increase");
    }
    int likes_count = this.likesProc.likes_count(reviewno);
    response.put("likes_count", likes_count);

    return response;
  }

}
