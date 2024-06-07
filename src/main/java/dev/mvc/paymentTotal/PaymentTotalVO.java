package dev.mvc.paymentTotal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import dev.mvc.member.MemberVO;
import dev.mvc.payment.PaymentVO;
import dev.mvc.paymentDetails.PaymentDetailsVO;
import dev.mvc.shoes.ShoesVO;
import dev.mvc.shoesFile.ShoesFileVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import dev.mvc.option.OptionVO;

@Getter @Setter @ToString
public class PaymentTotalVO {
  private Integer memberno;
  
  private Integer paymentno;
  private Date rdate;
  private String status;
  private String payment_status;
  private String cs_status;
  private Double total_price;
  private Double delivery;
  private Double total_payment;
  
  private ArrayList<HashMap<String, Object>> payment_details_option;
  
  private Integer shoesno;
  private String title;
  private String brand;
  private Double rating;
  private Double price;
  private Double discount;
  private String contents;
  private char visible;
  
  private Integer shoes_file_no;
  private String name;
  private Long shoes_file_sizes;
  private String ex;
  private String src;
}
