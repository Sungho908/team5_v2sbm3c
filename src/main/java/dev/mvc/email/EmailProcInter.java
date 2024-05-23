package dev.mvc.email;

public interface EmailProcInter {
  public void send(String sendTO, String sub, String con) throws Exception;
}
