package theo.versand_spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class WebAppTest {
  private final WebController controller;

  @Autowired
  WebAppTest(WebController controller){
    this.controller = controller;
  }

  @Test
  @DisplayName("Testet ob WebController existiert")
  public void test_0(){
    assertThat(this.controller).isNotNull();
  }
}
