package dev.mvc.notice;

import java.util.ArrayList;
import java.util.Map;

import dev.mvc.noticeFile.NoticeFileVO;

public interface NoticeDAOInter {

  /** 공지사항 생성 */
  public int create(NoticeFileVO noticefileVO);
  
  /** 공지사항 목록 */
  public ArrayList<NoticeMemberVO> list_all();
  
  
  /** 검색 개수 */
  public int list_search_count(String word);
  
  public ArrayList<NoticeMemberVO> list_search_paging(Map<String, Object> map);
  
  /** 파일 유무 */
  public int file_count(int noticeno);
  
  /** 공지사항 상세 */
  public NoticeMemberFileVO read(Map<String, Object> map);
  
  /** 조회수 증가 */
  public int increased_views(NoticeVO noticeVO);
  
  /** 공지사항 수정 */
  public int update(NoticeMemberFileVO noticememberfileVO);
  
  /** 공지사항 삭제 */
  public int delete(int noticeno);
  
  /** 공지사항-파일 삭제 */
  public int delete_file(int noticeno);
  
  /** 공지사항 조회수 증가 */
  public int increased_views(int noticeno);
}
