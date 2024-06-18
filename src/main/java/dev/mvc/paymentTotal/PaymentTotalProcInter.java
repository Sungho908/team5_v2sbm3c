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
   * 주문목록 count 가져오기
   * id="count" resultType="int"
   * @return 주문정보가 있는 멤버들의 count
   * */
  public int count(String word);
  

  /**
   * 페이징처리를 위한 admin 주문목록
   * id="listAdminPaging" resultType="dev.mvc.paymentTotal.PaymentTotalVO" parameterType="Map"
   * @param word             검색할 word
   * @param now_page         현재 페이지
   * @param record_per_page  검색할 레코드 갯수
   * @return ArrayList&lt;PaymentTotalVO&gt; 객체 
   * */
  public ArrayList<PaymentTotalVO> listAdminPaging(String word, int now_page, int record_per_page);
  
  
  /**
   * admin 주문목록 상세정보 가져오기 (Ajax)
   * id="listAdminPDO" parameterType="int" resultMap="PaymentTotalMap"
   * @param memberno 조회할 memberno
   * @return ArrayList&lt;PaymentTotalVO&gt; 객체
   * */
  public ArrayList<PaymentTotalVO> listAdminPDO(int memberno);

  public String ajaxStr(int memberno);
}
