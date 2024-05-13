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

import jakarta.validation.Valid;

@RequestMapping("/shoes")
@Controller
public class ShoesCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;
  
  public ShoesCont() {
    System.out.println("-> ShoesCont created.");  
  }
  
//  @GetMapping(value="/create") // http://localhost:9091/shoes/create
//  @ResponseBody
//  public String create() {
//    return "<h2>Create test.</h2>";    
//  }

  // create 폼 출력
  @GetMapping(value="/create") // http://localhost:9091/shoes/create
  public String create(ShoesVO shoesVO) {
    return "/shoes/create"; // /templates/shoes/create.html
  }
  
  // create 폼 데이터 처리
  @PostMapping(value="/create") // http://localhost:9091/shoes/create
  public String create(Model model, @Valid ShoesVO shoesVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "/shoes/create";  // /templates/shoes/create.html
    }
    
    int cnt = this.shoesProc.create(shoesVO);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      model.addAttribute("code", "create_success");
      model.addAttribute("name", shoesVO.getName());
      model.addAttribute("namesub", shoesVO.getNamesub());
    } else {
      model.addAttribute("code", "create_fail");
    }
    
    model.addAttribute("cnt", cnt);
    return "/shoes/msg"; // /templates/shoes/msg.html
  }
  
  @GetMapping(value="/list_all")
  public String list_all(Model model) {
    ArrayList<ShoesVO> list = this.shoesProc.list_all();
    model.addAttribute("list", list);
    
    return "/shoes/list_all"; // /shoes/list_all.html
  }

  /**
   * 조회
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value="/read/{shoesno}")
  public String read(Model model, @PathVariable("shoesno") Integer shoesno) {
    ShoesVO shoesVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesVO", shoesVO);
    
    return "/shoes/read";  // /templates/shoes/read.html
    
  }
  
  /**
   * 수정폼
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value="/update/{shoesno}")
  public String update(Model model, @PathVariable("shoesno") Integer shoesno) {
    ShoesVO shoesVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesVO", shoesVO);
    
    return "/shoes/update";  // /templates/shoes/update.html
    
  }
  
  /**
   * 수정 처리
   * @param model
   * @param shoesVO
   * @param bindingResult
   * @return
   */
  @PostMapping(value="/update") // http://localhost:9091/shoes/update
  public String update(Model model, @Valid ShoesVO shoesVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "/shoes/update";  // /templates/shoes/update.html
    }
    
    int cnt = this.shoesProc.update(shoesVO);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      model.addAttribute("code", "update_success");
      model.addAttribute("name", shoesVO.getName());
      model.addAttribute("namesub", shoesVO.getNamesub());
    } else {
      model.addAttribute("code", "update_fail");
    }
    
    model.addAttribute("cnt", cnt);
    return "/shoes/msg"; // /templates/shoes/msg.html
  }
  
  /**
   * Delete form
   * http://localhost:9091/shoes/delete/1
   * @param model
   * @param shoesno Shoesgory number to delete.
   * @return
   */
  @GetMapping(value="/delete/{shoesno}")
  public String delete(Model model, @PathVariable("shoesno") Integer shoesno) {
    ShoesVO shoesVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesVO", shoesVO);
    
    return "/shoes/delete";  // /templates/shoes/delete.html
    
  }
  
  /**
   * Delete process
   * @param model
   * @param shoesno 삭제할 레코드 번호
   * @param bindingResult
   * @return
   */
  @PostMapping(value="/delete")
  public String delete_process(Model model, Integer shoesno) {
    ShoesVO shoesVO = this.shoesProc.read(shoesno); // 삭제 정보 출력용 객체
    // model.addAttribute("shoesVO", shoesVO);
    
    int cnt = this.shoesProc.delete(shoesno); // 삭제
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      model.addAttribute("code", "delete_success");
      model.addAttribute("name", shoesVO.getName());
      model.addAttribute("namesub", shoesVO.getNamesub());
    } else {
      model.addAttribute("code", "delete_fail");
    }
    
    model.addAttribute("cnt", cnt);
    return "/shoes/msg"; // /templates/shoes/msg.html
  }
}









