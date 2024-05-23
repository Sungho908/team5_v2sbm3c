package dev.mvc.paymentInquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.paymentInquiry.PaymentInquiryProc")
public class PaymentInquiryProc implements PaymentInquiryProcInter{

  @Autowired
  private PaymentInquiryProcInter paymentInquiryDAO;
  
}
