package dev.mvc.paymentTotal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PaymentDetailsOptionVO {
  private Integer payment_details_no;
  private Integer payment_amount;
  private Integer optionno;
  private Integer sizes;
  private Integer amount;
  private String color;
}
