package dev.mvc.admin.category;

import java.util.ArrayList;
import java.util.Map;

public interface CategoryProcInter {

  public int create(CategoryVO categoryVO);
  
  public ArrayList<CategoryVO> list_all();
  
  public CategoryVO read(int c_no);
  
  public int delete(int c_no);
  
  public ArrayList<CategoryVO> list_all_name();
  
  public ArrayList<CategoryVO> list_all_subname(String c_name);
  
  public ArrayList<CategoryVO> list_search(String word);
  
  public ArrayList<CategoryVO> list_search_paging(String word, int now_page, int record_per_page);
  
  public int list_search_count(String word);
  
  public String pagingBox(int now_page, String word, String list_file, int search_count, 
      int record_per_page, int page_per_block);
}
