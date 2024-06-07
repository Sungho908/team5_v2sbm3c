package dev.mvc.member.payment;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
  
  @ResponseBody
  @PostMapping("delete")
  public ResponseEntity<String> delete(@RequestParam("no")int paymentno){
    if(this.paymentProc.delete(paymentno) == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문내역을 찾을 수 없음");
    }
    
    return ResponseEntity.ok("삭제 성공");
  }
}
