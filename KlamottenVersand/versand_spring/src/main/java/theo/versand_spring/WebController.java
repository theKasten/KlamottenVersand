package theo.versand_spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

  @GetMapping("/")
  public String index(Model m, int nr){
    m.addAttribute("nr", nr);
    return "index";
  }

}
