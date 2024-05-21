package dev.mvc.admin.notice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.notice.NoticeMemberVO;
import dev.mvc.notice.NoticeProcInter;
import dev.mvc.notice.NoticeVO;
import dev.mvc.noticeFile.Notice;
import dev.mvc.noticeFile.NoticeFileVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/admin/notice")
@Controller
public class AdminNoticeCont {

  @Autowired
  @Qualifier("dev.mvc.notice.NoticeProc")
  private NoticeProcInter noticeProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public AdminNoticeCont() {
    System.out.println("-> Notice created.");
  }

  private void table_paging(Model model, String word, int now_page) {
    ArrayList<NoticeMemberVO> list = this.noticeProc.list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);
    if (list.isEmpty()) {
    } else {
      model.addAttribute("list", list);

      int search_count = this.noticeProc.list_search_count(word);
      String paging = this.noticeProc.pagingBox(now_page, word, "/admin/notice/list", search_count,
          this.record_per_page, this.page_per_block);
      
      int no = search_count - ((now_page - 1) * this.record_per_page);

      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      model.addAttribute("word", word);
      model.addAttribute("no", no);

    }

  }

  @GetMapping(value = "list")
  public String list(HttpSession session, Model model, NoticeMemberVO noticeVO,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    word = Tool.checkNull(word).trim();

    ArrayList<NoticeMemberVO> menu = this.noticeProc.list_all();
    model.addAttribute("menu", menu);

    table_paging(model, word, now_page);

    return "admin/notice/list";
  }

  /** 공지사항 생성폼 */
  @GetMapping(value = "/create")
  public String create(HttpSession session, Model model) {

    NoticeVO noticeVO = new NoticeVO();
    model.addAttribute("noticeVO", noticeVO);
    return "admin/notice/create";
  }

  /** 공지사항 생성 */
  @PostMapping(value = "/create")
  public String create_process(HttpServletRequest request, HttpSession session, Model model, NoticeVO noticeVO,
      NoticeFileVO noticefileVO, RedirectAttributes ra) {

    String file1 = ""; // 원본 파일명 image
    String file1saved = ""; // 저장된 파일명, image
    String thumb1 = ""; // preview image

    String upDir = Notice.getUploadDir(); // 파일을 업로드할 폴더 준비
    System.out.println("-> upDir: " + upDir);

    MultipartFile mf = noticefileVO.getFileSelect();
    file1 = mf.getOriginalFilename(); // 원본 파일명 산출

    long size1 = mf.getSize();
    if (size1 > 0) {
      if (Tool.checkUploadFile(file1) == true) {
        file1saved = Upload.saveFileSpring(mf, upDir);

        if (Tool.isImage(file1saved)) {
          thumb1 = Tool.preview(upDir, file1saved, 200, 150);
        }

        noticefileVO.setName(file1);
        noticefileVO.setSize(size1);
        noticefileVO.setEx(file1saved);
        noticefileVO.setSrc(thumb1);
      } else {
        ra.addFlashAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
        ra.addFlashAttribute("cnt", 0); // 업로드 실패
        ra.addFlashAttribute("url", "/contents/msg"); // msg.html, redirect parameter 적용
        return "redirect:/admin/notice/msg"; // Post -> Get - param...
      }

    } else {
      System.out.println("-> 글만 등록");
    }

    // int memberno = (int) session.getAttribute("memberno");
    noticeVO.setMemberno(1); // 로그인 성공 시 변경
    int cnt = this.noticeProc.create(noticeVO);

    if (cnt == 1) {
      ra.addAttribute("noticeno", noticeVO.getNoticeno());
      return "redirect:/admin/notice/list";
    }

    return "admin/category/msg";
  }
}
