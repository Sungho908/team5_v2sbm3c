package dev.mvc.admin.category;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.tool.Tool;

@RequestMapping("/admin/category")
@Controller
public class CategoryCont {

  @Autowired
  @Qualifier("dev.mvc.admin.category.CategoryProc")
  private CategoryProcInter categoryProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public CategoryCont() {
    System.out.println("-> Category created.");
  }

  /** 카테고리 목록 */
  @GetMapping(value = "/list")
  public String category_list(Model model, CategoryVO categoryVO, 
                                  @RequestParam(name="word", defaultValue="") String word,
                                  @RequestParam(name="now_page", defaultValue="1") int now_page) {
    word = Tool.checkNull(word).trim();
    
    ArrayList<CategoryVO> menu = this.categoryProc.list_all();
    model.addAttribute("menu", menu);
    
    ArrayList<CategoryVO> list = this.categoryProc.list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);
    
    int search_count = this.categoryProc.list_search_count(word);
    
    String paging = this.categoryProc.pagingBox(now_page, 
        word, "/admin/category/list", search_count, this.record_per_page, this.page_per_block);
    model.addAttribute("paging", paging);
    model.addAttribute("now_page", now_page);
    
    model.addAttribute("word", word);
    
    int no = search_count - ((now_page - 1) * this.record_per_page);
    System.out.println("no: " +no);
    model.addAttribute("no", no);
    
    return "admin/category/list";
  }

}
