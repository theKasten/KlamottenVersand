package theo.versandt_core;

import org.javamoney.moneta.Money;

import java.util.List;
import java.util.Objects;

public class Bestellung {
  private List<Produkt> produkte;
  private Versand versandOption = Versand.STANDARD;
  private int nr;

  public Bestellung(int nr, List<Produkt> produkte) {
    this.produkte = produkte;
    this.nr = nr;
  }

  public Money versandKosten(){
    return versandOption.getKosten();
  }

  public Money preis() {
    return produkte.stream()
            .map(p -> p.einzelPreis().multiply(p.anzahl()))
            .reduce(Money::add)
            .orElse(Money.of(0, "EUR"));
  }

  public Money preisMitVersand() {
    return preis().add(versandKosten());
  }

  public List<Produkt> getProdukte(){
    return this.produkte;
  }

  public Money reduzierterPreis(double rabattInProzent) {
    Money rabatt = preis().multiply(rabattInProzent);
    return preis().subtract(rabatt);
  }

  public void setVersandOption(Versand versandOption) {
    this.versandOption = versandOption;
  }

  public Versand getVersandOption() {
    return this.versandOption;
  }

  public boolean isVersandOptionStandard(){
    if(versandOption == Versand.STANDARD){
      return true;
    }else {
      return false;
    }
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Bestellung that = (Bestellung) o;
    return nr == that.nr;
  }

  @Override
  public int hashCode() {
    return Objects.hash(nr);
  }
}
