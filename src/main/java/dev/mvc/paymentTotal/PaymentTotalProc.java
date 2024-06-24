package dev.mvc.paymentTotal;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
    List<PaymentTotalVO> paymentTotalList = new ArrayList<PaymentTotalVO>();

    // 결과를 처리할 핸들러 정의
    sqlsession.select("dev.mvc.paymentTotal.PaymentTotalDAOInter.list", map,
        (ResultHandler<PaymentTotalVO>) context -> {
          PaymentTotalVO paymentTotal = context.getResultObject();

          // 자식 쿼리 호출
          HashMap<String, Object> childMap = new HashMap<>();
          childMap.put("paymentno", paymentTotal.getPaymentno());
          childMap.put("search", search);

          ArrayList<PaymentDetailsOptionVO> paymentDetailsList = (ArrayList) sqlsession
              .selectList("dev.mvc.paymentTotal.PaymentTotalDAOInter.selectPaymentDetailsByPaymentNo", childMap);

          // paymentDetailsList가 비어있지 않으면 paymentTotalList에 추가
          if (!paymentDetailsList.isEmpty()) {
            paymentTotal.setPayment_details_option(paymentDetailsList);
            paymentTotalList.add(paymentTotal);
          }
        });

    return (ArrayList<PaymentTotalVO>) paymentTotalList;
  }

  @Override
  public int count(String word) {
    return this.paymentTotalDAO.count(word);
  }

  @Override
  public ArrayList<PaymentTotalVO> listAdminPaging(String word, int now_page, int record_per_page) {
    int begin_of_page = (now_page - 1) * record_per_page;
    int start_num = begin_of_page + 1;
    int end_num = begin_of_page + record_per_page;

    Map<String, Object> map = new HashMap<>();
    map.put("word", word);
    map.put("start_num", start_num);
    map.put("end_num", end_num);

    return this.paymentTotalDAO.listAdminPaging(map);
  }

  @Override
  public ArrayList<PaymentTotalVO> listAdminPDO(int memberno) {
    return this.paymentTotalDAO.listAdminPDO(memberno);
  };

  @Override
  public String ajaxStr(int memberno) {
    StringBuffer str = new StringBuffer();

    ArrayList<PaymentTotalVO> list = this.listAdminPDO(memberno);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DecimalFormat df = new DecimalFormat("###,###원");

    String[] payment_statuses = { "입금전", "추가입금대기", "입금완료(수동)", "입금완료(자동)", "결제완료" };
    String[] statuses = { "상품준비중", "배송준비중", "배송보류", "배송대기", "배송중", "배송완료" };
    String[] cs_statuses = { "", "취소", "교환", "반품", "환불" };

    for (PaymentTotalVO payment : list) {

      String current_payment_status = payment.getPayment_status();
      String current_status = payment.getStatus();
      String current_cs_status = payment.getCs_status();

//      str.append("<div class=\"contents\" id=\"btnDiv\">");

      str.append(
          "  <button type=\"button\" class=\"collapsible\" onclick=\"collapseChild(this);\" style=\"padding: 5px;\">");
      str.append("    <div style=\"padding: 5px 30px 5px 30px;\">");
      str.append("      <div style=\"float: left;\">");
      str.append("        <span class=\"color-white block\" >주문번호: " + payment.getPaymentno() + "</span>");
      for (PaymentDetailsOptionVO pdo : payment.getPayment_details_option()) {
        str.append("        <div>");
        str.append("          <span class=\"color-white\" style=\"width: 120px; float: left;\">주문상세번호: "
            + pdo.getPayment_details_no() + "</span>");
        str.append("          <span class=\"color-white\">상 품 명:" + pdo.getTitle() + " </span>");
        str.append("        </div>");
      }
      str.append(
          "        <span class=\"color-white block\">주문일자: " + simpleDateFormat.format(payment.getRdate()) + "</span>");
      str.append("      </div>");
      str.append("      <div style=\"float: right;\">");
      str.append("        <span class=\"color-white block\">결제상태: " + payment.getPayment_status() + "</span>");

      str.append("        <span class=\"color-white block\">주문상태: " + payment.getStatus() + "</span>");
      str.append("        <span class=\"color-white block\">C S 상태: "
          + (payment.getCs_status() != null ? payment.getCs_status() : "NULL") + "</span>");
      str.append("      </div>");
      str.append("    </div>");
      str.append("  </button>");

      str.append("  <div class=\"contents\">");
      str.append("    <div class=\"clearfix\" style=\"margin-top: 10px;\">");
      str.append("      <div>");
      str.append("        <span>총 상품가격: " + df.format(payment.getTotal_price()) + "&nbsp;&nbsp;&nbsp;&nbsp;" + "배송비: "
          + df.format(payment.getDelivery()) + "&nbsp;&nbsp;&nbsp;&nbsp;" + " 총 주문가격: "
          + df.format(payment.getTotal_payment()) + "</span>");
      str.append("      </div>");
      str.append("      <div style=\"float: left;\">");
      str.append("        <div style=\"display: flex; align-items: center;\">");
      str.append("          <label style=\"width: 120px; float: left;\">결제상태 수정하기</label>");
      str.append("          <select style=\"height: 2rem; width: 10rem;\" name=\"payment_status\" id=\"selectbox\">");

      for (String status : payment_statuses) {
        if (status.equals(current_payment_status)) {
          str.append(String.format("            <option selected>%s</option>", status));
        } else {
          str.append(String.format("            <option>%s</option>", status));
        }
      }

      str.append("          </select>");
      str.append("        </div>");

      str.append("        <div style=\"display: flex; align-items: center;\">");
      str.append("          <label style=\"width: 120px; float: left;\">주문상태 수정하기</label>");
      str.append("          <select style=\"height: 2rem; width: 10rem;\" name=\"status\" id=\"selectbox\">");

      for (String status : statuses) {
        if (status.equals(current_status)) {
          str.append(String.format("            <option selected>%s</option>", status));
        } else {
          str.append(String.format("            <option>%s</option>", status));
        }
      }

      str.append("          </select>");
      str.append("        </div>");

      str.append("        <div style=\"display: flex; align-items: center;\">");
      str.append("          <label style=\"width: 120px; float: left;\">CS상태 수정하기</label>");
      str.append("          <select style=\"height: 2rem; width: 10rem;\" name=\"cs_status\" id=\"selectbox\">");

      for (String status : cs_statuses) {
        if (status.equals("")) {
          if (current_cs_status == null || current_cs_status == "") {
            str.append("            <option selected value=\"NULL\">NULL로 변경</option>");
          } else {
            str.append("            <option value=\"NULL\">NULL로 변경</option>");
          }
        } else if (status.equals(current_cs_status)) {
          str.append(String.format("            <option selected>%s</option>", status));
        } else {
          str.append(String.format("            <option>%s</option>", status));
        }
      }

      str.append("          </select>");
      str.append("        </div>");
      str.append("      </div>");

      str.append("      <div style=\"float:right;\">");
      str.append(
          "        <button style=\"position: relative; bottom: 0px; right: 0px; height: 56px; width: 8rem; border: 1px solid #bbb; background-color: #e63740; color:#fff\" onclick=\"submit(this)\" type=\"button\">적용하기</button>");
      str.append("      </div>");
      str.append("    </div>");

      str.append("    <!--/*주문 정보 기입란*/-->");
      str.append("    <hr>");
      for (PaymentDetailsOptionVO pdo : payment.getPayment_details_option()) {
        str.append("    <div>");
        str.append("      <table style=\"width: 100%;\">");
        str.append("        <colgroup>");
        str.append("          <col style=\"width: 33%;\" />");
        str.append("          <col style=\"width: 33%;\" />");
        str.append("          <col style=\"width: 33%;\" />");
        str.append("        </colgroup>");
        str.append("        <tbody>");
        str.append("          <tr>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">신발번호:</span>");
        str.append("                <span>" + pdo.getShoesno() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">신발평점:</span>");
        str.append("                <span>" + pdo.getRating() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">상 품 명:</span>");
        str.append("                <span>" + pdo.getTitle() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("          </tr>");

        str.append("          <tr>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">주문상세번호:</span>");
        str.append("                <span>" + pdo.getPayment_details_no() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">할 인 률:</span>");
        str.append("                <span>" + String.format("%.1f", pdo.getDiscount()) + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">브랜드명:</span>");
        str.append("                <span>" + pdo.getBrand() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("          </tr>");

        str.append("          <tr>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">주문수량:</span>");
        str.append("                <span>" + pdo.getPayment_amount() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">신발가격:</span>");
        str.append("                <span>" + df.format(pdo.getPrice()) + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">사이즈 / 색상:</span>");
        str.append("                <span th:text=\"|${pdo.sizes} / ${pdo.color}|\">" + pdo.getSizes() + " / "
            + pdo.getColor() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("          </tr>");
        str.append("        </tbody>");
        str.append("      </table>");
        str.append("      <hr>");
        str.append("    </div>");
      }
      str.append("  </div>");
//      str.append("</div>");
    }
    return str.toString();

  }

}
