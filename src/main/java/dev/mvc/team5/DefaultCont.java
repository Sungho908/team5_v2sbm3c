package dev.mvc.team5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.mvc.tool.Alert;

@Controller
public class DefaultCont {
  @GetMapping({"","/"})
  public String index() {
    return "index";
  }
  
  /*
   * alert창을 띄움
   * */
  public static String showMessageAndRedirect(final Alert params, Model model) {
    model.addAttribute("params", params);
    return "alert";
  }
  
}
