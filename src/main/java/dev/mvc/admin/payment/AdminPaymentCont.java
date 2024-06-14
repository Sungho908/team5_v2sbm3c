package dev.mvc.admin.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.payment.PaymentProcInter;
import dev.mvc.paymentTotal.PaymentTotalProcInter;
import dev.mvc.paymentTotal.PaymentTotalVO;

@Controller
@RequestMapping("admin/payment")
public class AdminPaymentCont {
  @Autowired
  @Qualifier("dev.mvc.payment.PaymentProc")
  private PaymentProcInter paymentProc;
  
  @Autowired
  @Qualifier("dev.mvc.paymentTotal.PaymentTotalProc")
  private PaymentTotalProcInter paymentTotalProc;

  @GetMapping("list")
  public String list(Model model) {
    ArrayList<PaymentTotalVO> list = this.paymentTotalProc.listAdmin();
    Map<String, List<PaymentTotalVO>> groupedByMemberno = list.stream().collect(Collectors.groupingBy(PaymentTotalVO::getMemberid));
    model.addAttribute("paymentTotalLists", groupedByMemberno);
    return "admin/payment/list";
  }
  
  @ResponseBody
  @PostMapping("update")
  public String updateProc(@RequestBody Map<String, Object> map){
    if(this.paymentProc.update(map) < 1) {
      return "FAIL";
    }
    return "OK";
  }
}
