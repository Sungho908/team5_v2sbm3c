package dev.mvc.option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.option.OptionProc")
public class OptionProc implements OptionProcInter {

  @Autowired
  private OptionDAOInter optionDAO;

  @Override
  public int option_create(OptionVO optionVO) {
    int cnt = this.optionDAO.option_create(optionVO);
    return cnt;
  }

  @Override
  public int option_update(OptionVO optionVO) {
    int cnt = this.optionDAO.option_update(optionVO);
    return cnt;
  }

  @Override
  public int option_delete(int shoesno, int optionno) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("shoesno", shoesno);
    map.put("optionno", optionno);
    int cnt = this.optionDAO.option_delete(map);
    return cnt;
  }

  @Override
  public int option_search_count(int shoesno) {
    int cnt = this.optionDAO.option_search_count(shoesno);
    return cnt;
  }

  @Override
  public ArrayList<OptionVO> option_sizes(int shoesno, int categoryno) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("shoesno", shoesno);
    map.put("categoryno", categoryno);

    ArrayList<OptionVO> sizes = this.optionDAO.option_sizes(map);
    return sizes;
  }

  @Override
  public ArrayList<String> option_color(int shoesno, int categoryno) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("shoesno", shoesno);
    map.put("categoryno", categoryno);
    
    ArrayList<String> color = this.optionDAO.option_color(map);
    return color;
  }


}
