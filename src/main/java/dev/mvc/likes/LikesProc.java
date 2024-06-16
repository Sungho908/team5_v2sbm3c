package dev.mvc.likes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.likes.LikesProc")
public class LikesProc implements LikesProcInter{
  @Autowired
  private LikesDAOInter likesDAO;

  @Override
  public int create() {
    int cnt = this.likesDAO.create();
    return cnt;
  }

  @Override
  public int increased_likes(int reviewno) {
    int cnt = this.likesDAO.increased_likes(reviewno);
    return cnt;
  }

  @Override
  public int decreased_likes(int reviewno) {
    int cnt = this.likesDAO.decreased_likes(reviewno);
    return cnt;
  }

  @Override
  public int increased_hates(int reviewno) {
    int cnt = this.likesDAO.increased_hates(reviewno);
    return cnt;
  }

  @Override
  public int decreased_hates(int reviewno) {
    int cnt = this.likesDAO.decreased_hates(reviewno);
    return cnt;
  }

  @Override
  public int delete(int reviewno) {
    int cnt = this.likesDAO.delete(reviewno);
    return cnt;
  }

}
