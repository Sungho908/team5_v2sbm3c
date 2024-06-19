package dev.mvc.option;

import java.util.ArrayList;

public interface OptionProcInter {

  public int option_create(OptionVO optionVO);

  public int option_update(OptionVO optionVO);

  public int option_delete(int shoesno, int optionno);

  public int option_search_count(int shoesno);
  
  public ArrayList<Integer> option_sizes(int shoesno);
  
  public ArrayList<String> option_color(int shoesno);

}
