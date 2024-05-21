package dev.mvc.admin.notice;

import java.util.ArrayList;


public interface NoticeProcInter {

  /** 공지사항 생성 */
  public int create(NoticeVO noticeVO);
  
  /** 공지사항 목록 */
  public ArrayList<NoticeMemberVO> list_all();
  
  
  /** 검색 개수 */
  public int list_search_count(String word);
  
  public ArrayList<NoticeVO> list_search_paging(String word, int now_page, int record_per_page);
  
  /** 공지사항 상세 */
  public NoticeVO read(int noticeno);
  
  /** 조회수 증가 */
  public int increased_views(NoticeVO noticeVO);
  
  /** 공지사항 수정 */
  public int update(NoticeVO noticeVO);
  
  /** 공지사항 삭제 */
  public int delete(int noticeno);
  
  public String pagingBox(int now_page, String word, String list_file, int search_count, 
      int record_per_page, int page_per_block);
  
}
