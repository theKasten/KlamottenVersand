package theo.versand_spring;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class WebControllerTest {
  private final MockMvc mockMvc;

  @Autowired
  WebControllerTest(MockMvc mockMvc){
    this.mockMvc = mockMvc;
  }

  @Disabled
  @Test
  @DisplayName("Testet, ob unter / eine Webseite erreichbar ist die keine parameter erwartet")
  public void test_1() throws Exception {
    mockMvc.perform(get("/"))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Wird der parameter Nr richtig als attribut gespeichert?")
  public void test_2() throws Exception {
    mockMvc.perform(get("/").param("nr", "14"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("nr", 14));
  }

  @Test
  @DisplayName("")
  void test_3(){
  }

}
