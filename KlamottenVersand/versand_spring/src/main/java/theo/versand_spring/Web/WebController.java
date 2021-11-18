package theo.versand_spring.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import theo.versand_app.VersandService;
import theo.versandt_core.Bestellung;
import theo.versandt_core.Versand;

@Controller
public class WebController {

  private final VersandService service;

  public WebController(VersandService service) {
    this.service = service;
  }

  @GetMapping("/")
  public String index(Model m, int nr, @RequestParam(required = false, defaultValue ="STANDARD")Versand versandoption){
    Bestellung bestellung = service.getBestellung(nr, versandoption);

    m.addAttribute("bestellung", bestellung);
    m.addAttribute("nr", nr);

    return "index";
  }

  @PostMapping("/")
  public String versenden(int nr, @RequestParam(required = false, defaultValue ="STANDARD")Versand versandoption){
    service.bestellungVerschicken(nr, versandoption);
    return "redirect:/success.html";
  }

}
