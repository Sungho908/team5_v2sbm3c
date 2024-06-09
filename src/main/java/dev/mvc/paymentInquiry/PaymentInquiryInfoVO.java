package dev.mvc.paymentInquiry;

import dev.mvc.member.MemberVO;
import dev.mvc.option.OptionVO;
import dev.mvc.payment.PaymentVO;
import dev.mvc.shoes.ShoesVO;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PaymentInquiryInfoVO {
  private PaymentInquiryVO paymentInquiryVO;
  private PaymentVO paymentVO;
  private ShoesVO shoesVO;
  private OptionVO optionVO;
  private MemberVO memberVO;
}
