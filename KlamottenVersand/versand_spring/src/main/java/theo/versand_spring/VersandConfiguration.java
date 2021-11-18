package theo.versand_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import theo.versand_app.BestellungRepository;
import theo.versand_app.FulfillmentSystem;
import theo.versand_app.VersandService;

@Configuration
public class VersandConfiguration {
  @Bean
  public VersandService createService(BestellungRepository repository, FulfillmentSystem fulfillmentSystem){
    return new VersandService(repository, fulfillmentSystem);
  }
}
