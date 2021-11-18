package theo.versand_app.dto;

import theo.versandt_core.Produkt;
import org.javamoney.moneta.Money;

import java.util.List;
import java.util.stream.Collectors;

public record ProduktDto(String name, double einzelPreis, int anzahl) {
  public double gesamtPreis() {
    return this.einzelPreis * this.anzahl;
  }
  public Produkt toProdukt(){
    return new Produkt(name, Money.of(einzelPreis, "EUR"), anzahl);
  }

  public static List<Produkt> toProduktList(List<ProduktDto> produktDtoList){
    return produktDtoList.stream()
            .map(produktDto -> produktDto.toProdukt())
            .collect(Collectors.toList());
  }
}
