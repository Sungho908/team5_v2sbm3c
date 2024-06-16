package dev.mvc.paymentTotal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public interface PaymentTotalDAOInter {
  /**
   * 주문정보 가져오기
   * id="list" parameterType="Map" resultMap="PaymentTotalMap"
   * @param  map memberno, search, startDate, endDate
   * @return ArrayList&lt;PaymentTotalVO&gt; 객체
   * */
//  public ArrayList<PaymentTotalVO> list(HashMap<String, Object> map);
//  이벤트 핸들러 따로 구현
  
  
  /**
   * 어드민 주문정보 가져오기
   * id="listAdmin" parameterType="int" resultMap="PaymentTotalMap"
   * @param
   * @return 
   * */
  public ArrayList<PaymentTotalVO> listAdmin();
}
