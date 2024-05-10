package dev.mvc.shoes;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component("dev.mvc.shoes.ShoesProc")
@Service("dev.mvc.shoes.ShoesProc")
public class ShoesProc implements ShoesProcInter {
  @Autowired
  private ShoesDAOInter shoesDAO;
  
  public ShoesProc() {
    System.out.println("-> ShoesProc created.");  
  }
  
  @Override
  public int create(ShoesVO shoesVO) {
    int cnt = this.shoesDAO.create(shoesVO);
    return cnt;
  }

  @Override
  public ArrayList<ShoesVO> list_all() {
    ArrayList<ShoesVO> list = this.shoesDAO.list_all();
    return list;
  }

  @Override
  public ShoesVO read(int shoesno) {
    ShoesVO shoesVO = this.shoesDAO.read(shoesno);
    return shoesVO;
  }

  @Override
  public int update(ShoesVO shoesVO) {
    int cnt = this.shoesDAO.update(shoesVO);
    return cnt;
  }

  @Override
  public int delete(int shoesno) {
    int cnt = this.shoesDAO.delete(shoesno);
    return cnt;
  }
    

}

