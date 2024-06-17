package dev.mvc.option;

import java.util.ArrayList;
import java.util.Map;

public interface OptionDAOInter {

  public int option_create(OptionVO optionVO);

  public int option_update(OptionVO optionVO);

  public int option_delete(Map<String, Object> map);

  public int option_search_count(int shoesno);
  
  public ArrayList<Integer> option_sizes(int shoesno);

  public ArrayList<String> option_color(int shoesno);
}
