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
  public ArrayList<PaymentTotalVO> list(HashMap<String, Object> map);
  
  /**
   * 
   * id="selectPaymentDetailsByPaymentNo" parameterType="map" resultMap="PaymentDetailsOptionMap"
   * */
  public ArrayList<PaymentDetailsOptionVO> selectPaymentDetailsByPaymentNo(Map<String, Object> map);
  
  /**
   * 어드민 주문정보 가져오기
   * id="listAdmin" parameterType="int" resultMap="PaymentTotalMap"
   * @param
   * @return 
   * */
  public ArrayList<PaymentTotalVO> listAdmin(Map<String, Object> map);
  
  
  /**
   * 주문목록 count 가져오기
   * id="count" resultType="int" parameterType="word"
   * @return 주문정보가 있는 멤버들의 count
   * */
  public int count(String word);
  
  
  
  
  
  /**
   * 테스트중
   * id="test1" resultType="dev.mvc.paymentTotal.PaymentTotalVO" parameterType="Map"
   * */
  public ArrayList<PaymentTotalVO> test1(Map<String, Object> map);
  
  public ArrayList<PaymentTotalVO> test2(int memberno);
}
