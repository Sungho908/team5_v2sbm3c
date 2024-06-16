package dev.mvc.paymentTotal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public interface PaymentTotalProcInter {
  /**
   * 주문정보 가져오기
   * id="list" parameterType="int" resultType="Map"
   * @param memberno
   * */
  public ArrayList<PaymentTotalVO> list(int memberno, int dates, String search);
  
  /**
   * 어드민 주문정보 가져오기
   * id="listAdmin" parameterType="int" resultMap="PaymentTotalMap"
   * @param
   * @return 
   * */
  public ArrayList<PaymentTotalVO> listAdmin(String word);
}
