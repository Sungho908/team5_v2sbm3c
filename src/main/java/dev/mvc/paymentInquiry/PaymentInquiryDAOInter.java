package dev.mvc.paymentInquiry;

import java.util.ArrayList;
import java.util.Map;

public interface PaymentInquiryDAOInter {
  
  /**
   * 멤버 생성 id="create" parameterType="dev.mvc.paymentInquiry.PaymentInquiryVO"
   * 
   * @param paymentInquiryVO 객체
   * @return 성공한 쿼리 갯수
   */
  public int create(PaymentInquiryVO paymentInquiryVO);

  /**
   * 검색된 레코드 수 id="list_search_count" resultType="int" parameterType="String"
   * 
   * @param word 검색어
   * @return 성공한 쿼리 갯수
   */
  public int list_search_count(String word);

  /**
   * 페이징 id="list_search_paging" resultMap="InquiryResult" parameterType="Map"
   * 
   * @param Map
   * @return ArrayList<PaymentInquiryInfoVO>
   */
  public ArrayList<PaymentInquiryInfoVO> list_search_paging(Map<String, Object> map);

  /**
   * 기타 문의 상세 id="read" resultMap="InquiryResult" parameterType="Integer"
   * 
   * @param payment_inquiry_no
   * @return PaymentInquiryInfoVO
   */
  public PaymentInquiryInfoVO read(int payment_inquiry_no);

  /**
   * 답변 등록 id="answer" parameterType="Map"
   * 
   * @param Map<String, Object> map
   * @return int
   */
  public int answer(Map<String, Object> map);
}
