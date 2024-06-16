package dev.mvc.paymentTotal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mvc.tool.Tool;

@Service("dev.mvc.paymentTotal.PaymentTotalProc")
public class PaymentTotalProc implements PaymentTotalProcInter {
  @Autowired
  private PaymentTotalDAOInter paymentTotalDAO;

  @Autowired
  private SqlSession sqlsession;

  @Override
  public ArrayList<PaymentTotalVO> list(int memberno, int date, String search) {
    if (date > 0) {
      date = -date;
    }
    Date today = new Date();
    Date dates = Tool.addDays(today, date);

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("memberno", memberno);
    map.put("startDate", dates);
    map.put("endDate", today);
    map.put("search", search);

    // PaymentTotalVO 리스트를 담을 리스트 초기화
    List<PaymentTotalVO> paymentTotalList = new ArrayList<>();

    // 결과를 처리할 핸들러 정의
    sqlsession.select("dev.mvc.paymentTotal.PaymentTotalDAOInter.list", map, (ResultHandler<PaymentTotalVO>) context -> {
      PaymentTotalVO paymentTotal = context.getResultObject();

      // 자식 쿼리 호출
      HashMap<String, Object> childMap = new HashMap<>();
      childMap.put("paymentno", paymentTotal.getPaymentno());
      childMap.put("search", search);

      ArrayList<PaymentDetailsOptionVO> paymentDetailsList = (ArrayList) sqlsession.selectList("dev.mvc.paymentTotal.PaymentTotalDAOInter.selectPaymentDetailsByPaymentNo", childMap);

      // paymentDetailsList가 비어있지 않으면 paymentTotalList에 추가
      if (!paymentDetailsList.isEmpty()) {
        paymentTotal.setPayment_details_option(paymentDetailsList);
        paymentTotalList.add(paymentTotal);
      }
    });

    return (ArrayList<PaymentTotalVO>) paymentTotalList;
  }

  public ArrayList<PaymentTotalVO> listAdmin(String word) {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("word", word);

    // PaymentTotalVO 리스트를 담을 리스트 초기화
    List<PaymentTotalVO> paymentTotalList = new ArrayList<>();

    // 결과를 처리할 핸들러 정의
    sqlsession.select("dev.mvc.paymentTotal.PaymentTotalDAOInter.listAdmin", map, (ResultHandler<PaymentTotalVO>) context -> {
      PaymentTotalVO paymentTotal = context.getResultObject();

      // 자식 쿼리 호출
      HashMap<String, Object> childMap = new HashMap<>();
      childMap.put("paymentno", paymentTotal.getPaymentno());

      ArrayList<PaymentDetailsOptionVO> paymentDetailsList = (ArrayList) sqlsession.selectList("dev.mvc.paymentTotal.PaymentTotalDAOInter.selectPaymentDetailsByPaymentNo", childMap);

      // paymentDetailsList가 비어있지 않으면 paymentTotalList에 추가
      if (!paymentDetailsList.isEmpty()) {
        paymentTotal.setPayment_details_option(paymentDetailsList);
        paymentTotalList.add(paymentTotal);
      }
    });

    return (ArrayList<PaymentTotalVO>) paymentTotalList;
  }
}
