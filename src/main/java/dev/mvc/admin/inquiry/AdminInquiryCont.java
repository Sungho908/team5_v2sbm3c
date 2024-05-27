package dev.mvc.admin.inquiry;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.shoesInquiry.ShoesInquiryInfoVO;
import dev.mvc.shoesInquiry.ShoesInquiryProcInter;
import dev.mvc.shoesInquiry.ShoesInquiryVO;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/admin/inquiry")
@Controller
public class AdminInquiryCont {

  @Autowired
  @Qualifier("dev.mvc.shoesInquiry.ShoesInquiryProc")
  private ShoesInquiryProcInter shoesinquiryProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public AdminInquiryCont() {
    System.out.println("-> AdminInquiry created.");
  }
  
  private void shoes_table_paging(Model model, String word, int now_page) {
    ArrayList<ShoesInquiryInfoVO> list = this.shoesinquiryProc.list_search_paging(word, now_page, this.record_per_page);

    if (list.isEmpty()) {
    } else {
      model.addAttribute("list", list);
      
      int search_count = this.shoesinquiryProc.list_search_count(word);
      String paging = this.shoesinquiryProc.pagingBox(now_page, word, "/admin/category/list", search_count,
          this.record_per_page, this.page_per_block);
      
      int no = search_count - ((now_page - 1) * this.record_per_page);
      
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      model.addAttribute("word", word);
      model.addAttribute("no", no);

    }
  }
  
  /** 신발 문의 목록 */
  @GetMapping(value = "/shoes")
  public String shoes_list(HttpSession session, Model model,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    word = Tool.checkNull(word).trim();
    shoes_table_paging(model, word, now_page);

    return "admin/inquiry/shoes/list";
  }
  
  /** 신발 문의 읽기 */
  @GetMapping(value = "/shoes/{shoes_inquiry_no}")
  public String shoes_read(HttpSession session, Model model, @PathVariable("shoes_inquiry_no") Integer shoes_inquiry_no,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    
    ShoesInquiryInfoVO shoesInquiryInfoVO = this.shoesinquiryProc.read(shoes_inquiry_no);
    model.addAttribute("shoesInquiryInfoVO", shoesInquiryInfoVO);
    
    shoes_table_paging(model, word, now_page);
    return "admin/inquiry/shoes/read";
  }
  
  /** 신발 문의 읽기 */
  @PostMapping(value = "/shoes")
  public String shoes_answer(HttpSession session, Model model, 
      @Valid ShoesInquiryInfoVO shoesInquiryInfoVO, BindingResult bindingResult,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    ShoesInquiryVO shoesInquiryVO = shoesInquiryInfoVO.getShoesInquiryVO();
    
    this.shoesinquiryProc.answer(shoesInquiryVO.getShoes_inquiry_no(), 'Y', shoesInquiryVO.getAnswer_contents());
    return "redirect:/admin/inquiry/shoes/" + shoesInquiryVO.getShoes_inquiry_no()
        + "?word=" + word + "&now_page=" + now_page;
  }
  
}
