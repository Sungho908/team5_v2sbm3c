package dev.mvc.basket;

import java.util.ArrayList;

public interface BasketDAOInter {

  public ArrayList<BasketVO> getBasket(int memberno);
}
