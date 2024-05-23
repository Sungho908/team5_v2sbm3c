package dev.mvc.otherInquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.otherInquiry.OtherInquiryProc")
public class OtherInquiryProc implements OtherInquiryProcInter{

  @Autowired
  private OtherInquiryProcInter otherInquiryDAO;
  
}
