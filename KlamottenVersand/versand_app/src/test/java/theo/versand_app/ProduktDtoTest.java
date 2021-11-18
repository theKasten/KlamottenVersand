package theo.versand_app;

import theo.versand_app.dto.ProduktDto;
import theo.versandt_core.Produkt;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProduktDtoTest {
  @Test
  @DisplayName("Testet ob ein Produkt Dto auch korrekt in ein PProdukt objekt umgewandelt wird")
  public void test_1(){
    ProduktDto dto = new ProduktDto("Pulli", 3.85, 2);
    Produkt produkt = dto.toProdukt();
    assertThat(produkt.name()).isEqualTo("Pulli");
    assertThat(produkt.einzelPreis()).isEqualTo(Money.of(3.85, "EUR"));
    assertThat(produkt.anzahl()).isEqualTo(2);
  }

  @Test
  @DisplayName("Testet ob eine Liste von Dtos auch korrekt in eine Liste von Produkten umgewandelt wird")
  public void test_2(){
    ProduktDto dto1 = new ProduktDto("Pulli", 3.85, 2);
    ProduktDto dto2 = new ProduktDto("Schuhe", 20, 1);

    List<ProduktDto> dtos = List.of(dto1, dto2);

    List<Produkt> produkte = ProduktDto.toProduktList(dtos);

    assertThat(produkte.toArray().length).isEqualTo(2);
    assertThat(produkte.get(0)).isEqualTo(new Produkt("Pulli", Money.of(3.85, "EUR"), 2));
    assertThat(produkte.get(1)).isEqualTo(new Produkt("Schuhe", Money.of(20, "EUR"), 1));
  }


}
