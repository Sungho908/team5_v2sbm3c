package dev.mvc.basket;

import java.util.ArrayList;

import dev.mvc.option.OptionVO;
import dev.mvc.shoes.ShoesAllVO;

public interface BasketProcInter {

  public ArrayList<ShoesAllVO> list(int memberno);
 
  
  public int create(int memberno, String color, int sizes);

  
  public int delete(int memberno, int basketno);


  public int update(int amount, int memberno, int basketno);

  public int decrease(OptionVO optionVO);
  
  public int increase(OptionVO optionVO);
}
