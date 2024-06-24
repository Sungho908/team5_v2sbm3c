package dev.mvc.paymentTotal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PaymentDetailsOptionVO {
  private Integer shoesno;
  private String title;
  private String brand;
  private Double rating;
  private Double price;
  private Double discount;
  private String contents;
  private char visible;
  
  private Integer payment_details_no;
  private Integer payment_amount;
  
  
  private Integer optionno;
  private Integer sizes;
  private Integer amount;
  private String color;
  
  private Integer shoes_file_no;
  private String name;
  private Long shoes_file_sizes;
  private String ex;
  private String src;
  
  
}