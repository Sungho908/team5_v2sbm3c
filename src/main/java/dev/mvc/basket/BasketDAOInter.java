package dev.mvc.basket;

import java.util.ArrayList;
import java.util.Map;

import dev.mvc.shoes.ShoesAllVO;

public interface BasketDAOInter {

  public ArrayList<ShoesAllVO> getBasket(Map<String, Object> map);
}
