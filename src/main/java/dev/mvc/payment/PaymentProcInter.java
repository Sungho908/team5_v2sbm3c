package dev.mvc.payment;

import java.util.ArrayList;

public interface PaymentProcInter {
  /**
   * 회원의 모든 주문내역 가져오기
   * id="list_all" parameterType="int" resultType="dev.mvc.payment.PaymentVO"
   * @param memberno
   * @return paymentVO 객체
   * */
  public ArrayList<PaymentVO> list_all(int memberno);
}
