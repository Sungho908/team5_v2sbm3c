package dev.mvc.admin.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.member.MemberVO;
import dev.mvc.payment.PaymentProcInter;
import dev.mvc.paymentTotal.PaymentTotalProc;
import dev.mvc.paymentTotal.PaymentTotalProcInter;
import dev.mvc.paymentTotal.PaymentTotalVO;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin/payment")
public class AdminPaymentCont {
  @Autowired
  @Qualifier("dev.mvc.payment.PaymentProc")
  private PaymentProcInter paymentProc;
  
  @Autowired
  @Qualifier("dev.mvc.paymentTotal.PaymentTotalProc")
  private PaymentTotalProcInter paymentTotalProc;
  
  /* =========================================== ModelAttribute START =========================================== */
  /**
   * 목록(List) 출력 <br>
   * http://localhost:9093/admin/member/** <br>
   * http://54.180.175.50:9093/admin/member/**
   * 
   * @param now_page 현재 페이지
   * @param word     검색어
   * @param key      키워드 선택
   */
  @ModelAttribute("list")
  public Map<String, List<PaymentTotalVO>> paymentlist(@RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page,
                                                @RequestParam(name = "word", required = false, defaultValue = "") String word) {
    
    // 모든 데이터를 가져옵니다.
    ArrayList<PaymentTotalVO> list = this.paymentTotalProc.listAdmin(word);

    // memberId로 그룹화합니다.
    Map<String, List<PaymentTotalVO>> groupedByMemberno = list.stream()
        .collect(Collectors.groupingBy(PaymentTotalVO::getMemberid));
    
    // 전체 페이지 수를 계산합니다.
    int totalGroups = groupedByMemberno.size();
    int totalPages = (int) Math.ceil((double) totalGroups / PaymentTotalVO.PAGE_PER_BLOCK);

    // 현재 페이지 값이 유효한지 확인합니다.
    if (now_page < 1) {
      now_page = 1;
    } else if (now_page > totalPages) {
      now_page = totalPages;
    }

    // 시작 인덱스와 끝 인덱스를 계산합니다.
    int start = (now_page - 1) * PaymentTotalVO.PAGE_PER_BLOCK;

    // 그룹화된 데이터에서 해당 페이지에 해당하는 부분만 추출합니다.
    Map<String, List<PaymentTotalVO>> paginatedGroup = groupedByMemberno.entrySet().stream()
        .skip(start)
        .limit(PaymentTotalVO.PAGE_PER_BLOCK)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    
    return paginatedGroup;
  }
  
  @ModelAttribute("count")
  public Integer count(@RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page,
                       @RequestParam(name = "word", required = false, defaultValue = "") String word) {
    
    // 모든 데이터를 가져옵니다.
    ArrayList<PaymentTotalVO> list = this.paymentTotalProc.listAdmin(word);

    // memberId로 그룹화합니다.
    Map<String, List<PaymentTotalVO>> groupedByMemberno = list.stream()
        .collect(Collectors.groupingBy(PaymentTotalVO::getMemberid));
    
    // 전체 페이지 수를 계산합니다.
    int totalGroups = groupedByMemberno.size();
    int totalPages = (int) Math.ceil((double) totalGroups / PaymentTotalVO.PAGE_PER_BLOCK);

    // 현재 페이지 값이 유효한지 확인합니다.
    if (now_page < 1) {
      now_page = 1;
    } else if (now_page > totalPages) {
      now_page = totalPages;
    }

    return groupedByMemberno.size();
  }
  
  
  @ModelAttribute("paging")
  public String paging(HttpServletRequest request, 
                       @RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page,
                       @RequestParam(name = "word",     required = false, defaultValue = "")  String word) {
    //모든 데이터를 가져옵니다.
    ArrayList<PaymentTotalVO> list = this.paymentTotalProc.listAdmin(word);
    
    // memberId로 그룹화합니다.
    Map<String, List<PaymentTotalVO>> groupedByMemberno = list.stream()
        .collect(Collectors.groupingBy(PaymentTotalVO::getMemberid));
    
    int count = groupedByMemberno.size();
    String path = request.getServletPath();
    
    
    return this.paymentProc.pagingBox(now_page, word, path, count);
  }



  /* =========================================== ModelAttribute END =========================================== */

  @GetMapping("list")
  public String list(@RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page,
                     @RequestParam(name = "word",     required = false, defaultValue = "")  String word) {
    return "admin/payment/list";
  }
  
  @PostMapping("list")
  public String listProc(@RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page,
                         @RequestParam(name = "word",     required = false, defaultValue = "")  String word) {
    return "redicect:/admin/payment/list?now_page=" + now_page + "&word=" + word;  
        }
  
  @ResponseBody
  @PostMapping("update")
  public String updateProc(@RequestBody Map<String, Object> map){
    if(this.paymentProc.update(map) < 1) {
      return "FAIL";
    }
    return "OK";
  }
}
