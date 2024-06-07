package dev.mvc.paymentTotal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface PaymentTotalDAOInter {
  /**
   * 주문정보 가져오기
   * id="list" parameterType="int" resultType="Map"
   * @param memberno
   * */
  public ArrayList<PaymentTotalVO> list(int memberno);
}
