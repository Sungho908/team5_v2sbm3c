package dev.mvc.basket;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Component("dev.mvc.shoes.ShoesProc")
@Service("dev.mvc.basket.BasketProc")
public class BasketProc implements BasketProcInter {
  @Autowired
  private BasketDAOInter basketDAO;

  public BasketProc() {
    // System.out.println("-> ShoesProc created.");
  }

  @Override
  public ArrayList<BasketVO> getBasket(int memberno) {
    ArrayList<BasketVO> list = basketDAO.getBasket(memberno);
    return list;
  }

  
  
}

