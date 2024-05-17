package dev.mvc.admin.category;

import java.util.ArrayList;
import java.util.Map;

public interface CategoryDAOInter {
  
  public int create(CategoryVO categoryVO);
  
  public ArrayList<CategoryVO> list_all();
  
  public CategoryVO read(int c_no);
  
  public int delete(int c_no);
  
  public ArrayList<CategoryVO> list_all_name();
  
  public ArrayList<CategoryVO> list_all_subname(String c_name);
  
  public ArrayList<CategoryVO> list_search(String word);
  
  public ArrayList<CategoryVO> list_search_paging(Map<String, Object> map);
  
  public int list_search_count(String word);
  
}
