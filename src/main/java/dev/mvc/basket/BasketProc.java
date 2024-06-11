package dev.mvc.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mvc.shoes.ShoesAllVO;

//@Component("dev.mvc.shoes.ShoesProc")
@Service("dev.mvc.basket.BasketProc")
public class BasketProc implements BasketProcInter {
  @Autowired
  private BasketDAOInter basketDAO;

  public BasketProc() {
    // System.out.println("-> ShoesProc created.");
  }

  @Override
  public ArrayList<ShoesAllVO>getBasket(int memberno) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("memberno", memberno);
    
    ArrayList<ShoesAllVO> list = this.basketDAO.getBasket(map);
    return list;
  }
  
  
}

