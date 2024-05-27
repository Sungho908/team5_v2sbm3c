package dev.mvc.member.payment;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.mvc.member.MemberVO;
import dev.mvc.payment.PaymentProcInter;
import dev.mvc.payment.PaymentVO;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member/payment")
public class MemberPaymentCont {
  @Autowired
  @Qualifier("dev.mvc.payment.PaymentProc")
  private PaymentProcInter paymentProc;
  
  @GetMapping("order")
  public String order(HttpSession session, Model model) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    
    ArrayList<PaymentVO> paymentList = this.paymentProc.list_all(memberVO.getMemberno());
    System.out.println("pay: " + paymentList);
    if(paymentList != null && !paymentList.isEmpty())
      model.addAttribute("paymentList", paymentList);
    return "member/payment/order";
  }
}
