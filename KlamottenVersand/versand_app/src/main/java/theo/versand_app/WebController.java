//package com.example.versand_app;
//
//import com.example.versand_app.dto.ProduktDto;
//import com.example.versandt_core.Bestellung;
//import com.example.versandt_core.Produkt;
//import com.example.versandt_core.Versand;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
//@Controller
//public class WebController {
//  @GetMapping("/")
//  public String index(Model m, String versandt){
//    Versand versandOption = Versand.STANDARD;
//
//    ProduktDto produktDto = new ProduktDto("Pullover", 15.00, 2);
//    ProduktDto produktDto1 = new ProduktDto("T-shirt", 10.00, 4);
//    ProduktDto produktDto2 = new ProduktDto("Schuhe", 56, 1);
//
//    List<ProduktDto> produktDtoList = List.of(produktDto1, produktDto, produktDto2);
//    List<Produkt> produkte = ProduktDto.toProduktList(produktDtoList);
//
//    if(versandt == null || versandt.equals("standard")) {
//      m.addAttribute("standardVersandt", true);
//      versandt = "standard";
//    }else{
//      m.addAttribute("standardVersandt", false);
//      versandOption = Versand.EXPRESS;
//    }
//
//    Bestellung bestellung = new Bestellung(1, produkte);
//    bestellung.setVersandOption(versandOption);
//
//    m.addAttribute("produkte", produktDtoList);
//
//    m.addAttribute("standardVersandKosten", Versand.STANDARD.getKosten().getNumber());
//    m.addAttribute("expressVersandKosten", Versand.EXPRESS.getKosten().getNumber());
//
//    m.addAttribute("bestellwert", bestellung.preis().getNumber());
//    m.addAttribute("versandtKosten", versandOption.getKosten().getNumber());
//    m.addAttribute("gesamtPreis", bestellung.preisMitVersand().getNumber());
//
//    return "index";
//  }
//
//  /*@GetMapping("/")
//  public String index(Model m, Bestellung bestellung){
//
//
//    m.addAttribute("produkte", bestellung.getProdukte());
//    //m.addAttribute("expressAufpreis", bestellung.getExpressVersandtAufpreis());
//    return "index";
//  }*/
//
//  /*@GetMapping("/")
//  public String index(Model m, DBService service){
//    Bestellung bestellung = DBService.getBestellung("3c52846a-449b-11ec-81d3-0242ac130003");
//    m.addAttribute("produkte", bestellung.getProdukte());
//    return "index";
//  }*/
//
//  /*@GetMapping("/")
//  public String indexGet(){
//    return "redirect:/?versandt=standard";
//  }*/
//
//  @PostMapping("/")
//  @ResponseBody
//  public String bestellen(){
//    return "Es wurde geordert";
//  }
//}
