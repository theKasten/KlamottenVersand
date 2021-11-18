package theo.versand_spring.Web;

import theo.versand_app.VersandService;
import theo.versandt_core.Bestellung;
import theo.versandt_core.Produkt;
import theo.versandt_core.Versand;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
public class WebControllerTest {
  private final MockMvc mockMvc;

  @MockBean
  VersandService service;

  @Autowired
  WebControllerTest(MockMvc mockMvc){
    this.mockMvc = mockMvc;
  }


  @Test
  @DisplayName("Wird der parameter Nr richtig als attribut gespeichert?")
  public void test_1() throws Exception {
    when(service.getBestellung(14, Versand.STANDARD)).thenReturn(new Bestellung(14, Collections.emptyList()));

    mockMvc.perform(get("/").param("nr", "14"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("nr", 14));
  }

  @Test
  @DisplayName("Produkt wird im html angezeigt")
  void test_2() throws Exception {
    Produkt produkt = new Produkt("jhkldfgsjklsdfgjkhl0u98r32rsdfhuio", Money.of(13, "EUR"), 98234);
    when(service.getBestellung(16, Versand.EXPRESS)).thenReturn(new Bestellung(16, List.of(produkt)));
    MvcResult result = mockMvc.perform(get("/")
            .param("nr", "16")
            .param("versandoption", Versand.EXPRESS.name()))
    .andReturn();
    String html = result.getResponse().getContentAsString();
    assertThat(html).contains("jhkldfgsjklsdfgjkhl0u98r32rsdfhuio");
    assertThat(html).contains("98234");
    assertThat(html).contains("13.00");
    assertThat(html).contains("" + (98234 * 13) + ".00");
  }

  @Test
  @DisplayName("Bestellung mit expressversand wird im html angezeigt")
  void test_3() throws Exception {
    Produkt produkt1 = new Produkt("jhkldfgsjklsdfgjkhl0u98r32rsdfhuio", Money.of(13, "EUR"), 98234);
    Produkt produkt2 = new Produkt("shidukf8934u2rwefsgdfsdgfe345serg", Money.of(18.45, "EUR"), 42);

    Bestellung bestellung = new Bestellung(16, List.of(produkt1, produkt2));
    bestellung.setVersandOption(Versand.EXPRESS);
    when(service.getBestellung(16, Versand.EXPRESS)).thenReturn(bestellung);

    MvcResult result = mockMvc.perform(get("/")
            .param("nr", "16")
            .param("versandoption", Versand.EXPRESS.name()))
    .andReturn();
    String html = result.getResponse().getContentAsString();
    assertThat(html).contains("jhkldfgsjklsdfgjkhl0u98r32rsdfhuio");
    assertThat(html).contains("98234");
    assertThat(html).contains("13.00");
    assertThat(html).contains("1277042.00");

    assertThat(html).contains("shidukf8934u2rwefsgdfsdgfe345serg");
    assertThat(html).contains("42");
    assertThat(html).contains("18.45");
    assertThat(html).contains("774.90");

    assertThat(html).contains("1277816.90");

    assertThat(html).contains("20.00");

    assertThat(html).contains("1277836.90");

    assertThat(html).contains("value=\"express\" checked=\"checked\"");
    assertThat(html).doesNotContain("value=\"standard\" checked=\"checked\"");
  }

  @Test
  @DisplayName("Bestellung mit Standardversand wird im html angezeigt")
  void test_4() throws Exception {
    Produkt produkt1 = new Produkt("sgdfr4532sgdtfr2345srdg", Money.of(84.35, "EUR"), 11);
    Produkt produkt2 = new Produkt("sdfg45tsgdr345wsgdf2543", Money.of(18.45, "EUR"), 8);

    Bestellung bestellung = new Bestellung(10, List.of(produkt1, produkt2));
    bestellung.setVersandOption(Versand.STANDARD);
    when(service.getBestellung(10, Versand.STANDARD)).thenReturn(bestellung);

    MvcResult result = mockMvc.perform(get("/")
            .param("nr", "10")
            .param("versandoption", Versand.STANDARD.name()))
    .andReturn();
    String html = result.getResponse().getContentAsString();
    assertThat(html).contains("sgdfr4532sgdtfr2345srdg");
    assertThat(html).contains("84.35");
    assertThat(html).contains("11");
    assertThat(html).contains("927.85");

    assertThat(html).contains("sdfg45tsgdr345wsgdf2543");
    assertThat(html).contains("18.45");
    assertThat(html).contains("8");
    assertThat(html).contains("147.60");

    assertThat(html).contains("1075.45");

    assertThat(html).contains("2.50");

    assertThat(html).contains("1077.95");

    assertThat(html).contains("value=\"standard\" checked=\"checked\"");
    assertThat(html).doesNotContain("value=\"express\" checked=\"checked\"");
  }

  @Test
  @DisplayName("Abschicken des Formulars mit expressversand wird verarbeitet")
  void test_5() throws Exception {
    mockMvc.perform(post("/")
            .param("nr", "16")
            .param("versandoption", Versand.EXPRESS.name()))
    .andExpect(status().is3xxRedirection());
    verify(service).bestellungVerschicken(16, Versand.EXPRESS);
  }

  @Test
  @DisplayName("Abschicken des Formulars mit satandard versand wird verarbeitet")
  void test_6() throws Exception {
    mockMvc.perform(post("/")
            .param("nr", "16"))
    .andExpect(status().is3xxRedirection());
    verify(service).bestellungVerschicken(16, Versand.STANDARD);
  }
}
