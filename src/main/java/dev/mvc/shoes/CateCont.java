package dev.mvc.shoes;

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
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;

@RequestMapping("/cate")
@Controller
public class CateCont {
  @Autowired
  @Qualifier("dev.mvc.cate.CateProc")
  private CateProcInter cateProc;
  
  public CateCont() {
    System.out.println("-> CateCont created.");  
  }
  
//  @GetMapping(value="/create") // http://localhost:9091/cate/create
//  @ResponseBody
//  public String create() {
//    return "<h2>Create test.</h2>";    
//  }

  // create 폼 출력
  @GetMapping(value="/create") // http://localhost:9091/cate/create
  public String create(CateVO cateVO) {
    return "/cate/create"; // /templates/cate/create.html
  }
  
  // create 폼 데이터 처리
  @PostMapping(value="/create") // http://localhost:9091/cate/create
  public String create(Model model, @Valid CateVO cateVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "/cate/create";  // /templates/cate/create.html
    }
    
    int cnt = this.cateProc.create(cateVO);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      model.addAttribute("code", "create_success");
      model.addAttribute("name", cateVO.getName());
      model.addAttribute("namesub", cateVO.getNamesub());
    } else {
      model.addAttribute("code", "create_fail");
    }
    
    model.addAttribute("cnt", cnt);
    return "/cate/msg"; // /templates/cate/msg.html
  }
  
  @GetMapping(value="/list_all")
  public String list_all(Model model) {
    ArrayList<CateVO> list = this.cateProc.list_all();
    model.addAttribute("list", list);
    
    return "/cate/list_all"; // /cate/list_all.html
  }

  /**
   * 조회
   * @param model
   * @param cateno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value="/read/{cateno}")
  public String read(Model model, @PathVariable("cateno") Integer cateno) {
    CateVO cateVO = this.cateProc.read(cateno);
    model.addAttribute("cateVO", cateVO);
    
    return "/cate/read";  // /templates/cate/read.html
    
  }
  
  /**
   * 수정폼
   * @param model
   * @param cateno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value="/update/{cateno}")
  public String update(Model model, @PathVariable("cateno") Integer cateno) {
    CateVO cateVO = this.cateProc.read(cateno);
    model.addAttribute("cateVO", cateVO);
    
    return "/cate/update";  // /templates/cate/update.html
    
  }
  
  /**
   * 수정 처리
   * @param model
   * @param cateVO
   * @param bindingResult
   * @return
   */
  @PostMapping(value="/update") // http://localhost:9091/cate/update
  public String update(Model model, @Valid CateVO cateVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "/cate/update";  // /templates/cate/update.html
    }
    
    int cnt = this.cateProc.update(cateVO);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      model.addAttribute("code", "update_success");
      model.addAttribute("name", cateVO.getName());
      model.addAttribute("namesub", cateVO.getNamesub());
    } else {
      model.addAttribute("code", "update_fail");
    }
    
    model.addAttribute("cnt", cnt);
    return "/cate/msg"; // /templates/cate/msg.html
  }
  
  /**
   * Delete form
   * http://localhost:9091/cate/delete/1
   * @param model
   * @param cateno Category number to delete.
   * @return
   */
  @GetMapping(value="/delete/{cateno}")
  public String delete(Model model, @PathVariable("cateno") Integer cateno) {
    CateVO cateVO = this.cateProc.read(cateno);
    model.addAttribute("cateVO", cateVO);
    
    return "/cate/delete";  // /templates/cate/delete.html
    
  }
  
  /**
   * Delete process
   * @param model
   * @param cateno 삭제할 레코드 번호
   * @param bindingResult
   * @return
   */
  @PostMapping(value="/delete")
  public String delete_process(Model model, Integer cateno) {
    CateVO cateVO = this.cateProc.read(cateno); // 삭제 정보 출력용 객체
    // model.addAttribute("cateVO", cateVO);
    
    int cnt = this.cateProc.delete(cateno); // 삭제
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      model.addAttribute("code", "delete_success");
      model.addAttribute("name", cateVO.getName());
      model.addAttribute("namesub", cateVO.getNamesub());
    } else {
      model.addAttribute("code", "delete_fail");
    }
    
    model.addAttribute("cnt", cnt);
    return "/cate/msg"; // /templates/cate/msg.html
  }
}









