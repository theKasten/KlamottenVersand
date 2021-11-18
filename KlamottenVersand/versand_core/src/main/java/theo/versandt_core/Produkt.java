package theo.versandt_core;

import org.javamoney.moneta.Money;

public record Produkt(String name, Money einzelPreis, int anzahl) {
  public Money gesamtPreis(){
    return einzelPreis.multiply(anzahl);
  }
}
