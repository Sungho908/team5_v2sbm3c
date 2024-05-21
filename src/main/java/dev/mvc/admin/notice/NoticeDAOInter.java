package dev.mvc.admin.notice;

import java.util.ArrayList;
import java.util.Map;

public interface NoticeDAOInter {

  /** 공지사항 생성 */
  public int create(NoticeVO noticeVO);
  
  /** 공지사항 목록 */
  public ArrayList<NoticeMemberVO> list_all();
  
  
  /** 검색 개수 */
  public int list_search_count(String word);
  
  public ArrayList<NoticeVO> list_search_paging(Map<String, Object> map);
  
  /** 공지사항 상세 */
  public NoticeVO read(int noticeno);
  
  /** 조회수 증가 */
  public int increased_views(NoticeVO noticeVO);
  
  /** 공지사항 수정 */
  public int update(NoticeVO noticeVO);
  
  /** 공지사항 삭제 */
  public int delete(int noticeno);
}
