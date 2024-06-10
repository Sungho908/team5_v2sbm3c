package dev.mvc.basket;

import java.util.ArrayList;

import dev.mvc.shoes.ShoesAllVO;

public interface BasketProcInter {

  public ArrayList<ShoesAllVO> getBasket(int basketno, int memberno);
  
}
