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
  
  /**
   * 주문목록 count 가져오기
   * id="count" resultType="int"
   * @return 주문정보가 있는 멤버들의 count
   * */
  public int count(String word);
  
  /**
   * 테스트중
   * id="test1" resultType="dev.mvc.paymentTotal.PaymentTotalVO" parameterType="String"
   * */
  public ArrayList<PaymentTotalVO> test1(String word, int now_page, int recode_per_page);
  
  public ArrayList<PaymentTotalVO> test2(int memberno);

  public String ajaxStr(int memberno);
}
