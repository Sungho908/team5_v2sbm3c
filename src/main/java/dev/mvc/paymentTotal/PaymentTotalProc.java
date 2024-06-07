package dev.mvc.paymentTotal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.paymentTotal.paymentTotalProc")
public class PaymentTotalProc implements PaymentTotalProcInter {
  @Autowired
  private PaymentTotalDAOInter paymentTotalDAO;

  @Override
  public ArrayList<PaymentTotalVO> list(int memberno) {
    ArrayList<PaymentTotalVO> list = this.paymentTotalDAO.list(memberno);
    for(PaymentTotalVO pay : list) {
    System.out.println(pay);  
    }
    
    return list;
  } 
}
