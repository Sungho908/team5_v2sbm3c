package dev.mvc.team5.util;


import org.springframework.stereotype.Component;

@Component(value = "StringUtils")
public class StringUtils {
  public String format(String format, Object... args) {
    return String.format(format, args);
  }
  
  public String formatnbsp(String format, Object... args) {
    String formattedString = String.format(format, args);
    return formattedString.replace(" ", "&nbsp;");
  }
}

