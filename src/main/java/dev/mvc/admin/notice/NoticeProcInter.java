package dev.mvc.admin.notice;

import java.util.ArrayList;

public interface NoticeProcInter {

  /** 공지사항 생성 */
  public int create(NoticeVO noticeVO);
  
  /** 공지사항 목록 */
  public ArrayList<NoticeVO> list_all();
  
  /** 공지사항 상세 */
  public NoticeVO read(int n_no);
  
  /** 검색 개수 */
  public int list_search_count(String word);
  
  /** 공지사항 수정 */
  public int update(NoticeVO noticeVO);
  
  /** 공지사항 삭제 */
  public int delete(int n_no);
  
  
}
