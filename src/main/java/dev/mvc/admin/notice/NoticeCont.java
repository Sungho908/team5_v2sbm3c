package dev.mvc.admin.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/notice")
@Controller
public class NoticeCont {

  @Autowired
  @Qualifier("dev.mvc.admin.notice.NoticeProc")
  private NoticeProcInter noticeProc;
  
  public NoticeCont() {
    System.out.println("-> Notice created.");
  }
  
  
}
