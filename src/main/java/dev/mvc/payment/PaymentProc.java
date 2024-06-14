package dev.mvc.payment;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.payment.PaymentProc")
public class PaymentProc implements PaymentProcInter {
  @Autowired
  private PaymentDAOInter paymentDAO;
  

  @Override
  public ArrayList<PaymentVO> list_all(int memberno) {
    return this.paymentDAO.list_all(memberno);
  }


  @Override
  public int delete(int paymentno) {
    return this.paymentDAO.delete(paymentno);
  }


  @Override
  public int update(Map<String, Object> map) {
    if(map.get("cs_status").equals("NULL")) {
      map.put("cs_status", "");
    }
    System.out.println("map:" + map);
    return this.paymentDAO.update(map);
  }

}
