package dev.mvc.paymentTotal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.paymentTotal.PaymentTotalProc")
public class PaymentTotalProc implements PaymentTotalProcInter {
  @Autowired
  private PaymentTotalDAOInter paymentTotalDAO;

  @Override
  public ArrayList<PaymentTotalVO> list(int memberno) {
    return this.paymentTotalDAO.list(memberno);
  } 
}
