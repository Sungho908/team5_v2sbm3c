package dev.mvc.team5;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mvc.paymentTotal.PaymentTotalProcInter;
import dev.mvc.paymentTotal.PaymentTotalVO;

@SpringBootTest
class Team5_v2sbm3cApplicationTests {
  
  @Autowired
  @Qualifier("dev.mvc.paymentTotal.paymentTotalProc")
  private PaymentTotalProcInter paymentTotalProc;
	
	@Test
	public void paymentTest() {
	  
	}

}
