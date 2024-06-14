package dev.mvc.likes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.mvc.review.ReviewProcInter;

@RequestMapping("/likes")
@Controller
public class LikesCont {
  
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc;
  
  @Autowired
  @Qualifier("dev.mvc.likes.LikesProc")
  private LikesProcInter likesProc;
  
  
}
