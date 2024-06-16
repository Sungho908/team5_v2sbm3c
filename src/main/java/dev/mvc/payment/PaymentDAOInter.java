package dev.mvc.payment;

import java.util.ArrayList;
import java.util.Map;

public interface PaymentDAOInter {
  /**
   * 회원의 모든 주문내역 가져오기
   * id="list_all" parameterType="int" resultType="dev.mvc.payment.PaymentVO"
   * @param memberno
   * @return paymentVO 객체
   * */
  public ArrayList<PaymentVO> list_all(int memberno);
  
  /**
   * 주문내역 삭제
   * id="delete" parameterType="int"
   * @param paymentno
   * @return 삭제 성공한 쿼리 갯수
   * */
  public int delete(int paymentno);
  
  /**
   * 주문내역 수정
   * id="update" parameterType="Map"
   * */
  public int update(Map<String, Object> map);
}
