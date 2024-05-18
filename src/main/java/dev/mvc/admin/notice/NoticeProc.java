package dev.mvc.admin.notice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.admin.notice.NoticeProc")
public class NoticeProc implements NoticeProcInter{

  @Autowired
  private NoticeDAOInter noticeDAO;
  
  @Override
  public int create(NoticeVO noticeVO) {
    int cnt = this.noticeDAO.create(noticeVO);
    return cnt;
  }

  @Override
  public ArrayList<NoticeVO> list_all() {
    ArrayList<NoticeVO> list = this.noticeDAO.list_all();
    return list;
  }

  @Override
  public NoticeVO read(int n_no) {
    NoticeVO list = this.noticeDAO.read(n_no);
    return list;
  }

  @Override
  public int list_search_count(String word) {
    int cnt = this.noticeDAO.list_search_count(word);
    return cnt;
  }

  @Override
  public int update(NoticeVO noticeVO) {
    int cnt = this.noticeDAO.update(noticeVO);
    return cnt;
  }

  @Override
  public int delete(int n_no) {
    int cnt = this.noticeDAO.delete(n_no);
    return cnt;
  }
  
}
