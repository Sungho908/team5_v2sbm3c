package dev.mvc.report;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/report")
@Controller
public class ReportCont {
  @Autowired
  @Qualifier("dev.mvc.report.ReportProc")
  private ReportProcInter reportProc;

  @PostMapping(value = "/report_count")
  @ResponseBody
  public int report_count(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    // session의 memberno 값 받아오기
    // session.getAttribute("memberno");
    int memberno = 1;
    int reviewno = Integer.parseInt(map.get("reviewno"));
    
    int cnt = this.reportProc.report_count(memberno, reviewno);
    return cnt;
  }
  @PostMapping(value = "/create")
  @ResponseBody
  public Map<String, Object> create(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    
    // session의 memberno 값 받아오기
    // session.getAttribute("memberno");
    
    // 신고 생성
    ReportVO reportVO = new ReportVO();
    reportVO.setTitle(map.get("title"));
    reportVO.setContents(map.get("contents"));
    reportVO.setTypeno(Integer.parseInt(map.get("typeno")));
    reportVO.setMemberno(1);
    reportVO.setReviewno(Integer.parseInt(map.get("reviewno")));
    int cnt = this.reportProc.create(reportVO);
    
    Map<String, Object> response = new HashMap<>();
    if(cnt == 1) {
      response.put("success", true);
    }
    return response;
  }
  
  @GetMapping("/myReport")
  public String myReport(HttpSession session, Model model) {
    
    return "report/myReport";
  }
}
