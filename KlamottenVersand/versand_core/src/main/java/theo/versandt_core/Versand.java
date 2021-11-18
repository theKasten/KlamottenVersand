package theo.versandt_core;

import org.javamoney.moneta.Money;

public enum Versand {
  EXPRESS(Money.of(20, "EUR")), STANDARD(Money.of(2.5, "EUR"));

  private Money kosten;

  Versand(Money kosten) {
    this.kosten = kosten;
  }

  public Money getKosten(){
    return this.kosten;
  }
}
