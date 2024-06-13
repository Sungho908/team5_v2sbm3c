package dev.mvc.basket;

import java.util.ArrayList;

import dev.mvc.shoes.ShoesAllVO;

public interface BasketProcInter {

  public ArrayList<ShoesAllVO> list(int memberno);
 
  
  public int create(int memberno, String color, int sizes);

  
  public int delete(int memberno, int basketno);


  public int update(BasketVO basketVO);

  
}
