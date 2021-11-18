package theo.versandt_core;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BestellungTest {

  @Test
  @DisplayName("Stellt sicher, das Bestellung ohne produkte einen gesamtwert von 0eur hat")
  public void test_0(){
    List<Produkt> produkte = new ArrayList<Produkt>();
    Bestellung bestellung = new Bestellung(1, produkte);
    bestellung.setVersandOption(Versand.STANDARD);
    assertThat(bestellung.preis()).isEqualTo(Money.of(0, "EUR"));
  }

  @Test
  @DisplayName("Stellt sicher, das Bestellung mit einem produkt einen gesamtwert von 50eur plus 5 eur versandkosten hat")
  public void test_1(){
    Money versandKosten = Money.of(5, "EUR");
    List<Produkt> produkte = new ArrayList<Produkt>();

    produkte.add(new Produkt("Pulli", Money.of(50, "EUR"), 1));

    Bestellung bestellung = new Bestellung(1, produkte);
    bestellung.setVersandOption(Versand.EXPRESS);
    assertThat(bestellung.preis()).isEqualTo(Money.of(50, "EUR"));
  }

  @Test
  @DisplayName("Stellt sicher, das Bestellung mit einem produkt einen gesamtwert von 50eur plus 5 eur versandkosten plus 3 mal 15 eur")
  public void test_2(){
    List<Produkt> produkte = new ArrayList<Produkt>();

    produkte.add(new Produkt("Pulli", Money.of(50, "EUR"), 1));
    produkte.add(new Produkt("T-shirt", Money.of(15, "EUR"), 3));

    Bestellung bestellung = new Bestellung(1, produkte);
    bestellung.setVersandOption(Versand.EXPRESS);
    assertThat(bestellung.preis()).isEqualTo(Money.of((50 + (15 * 3)), "EUR"));
  }


  @Test
  @DisplayName("Testet ob rabatt berechnet wird")
  public void test_3(){
    List<Produkt> produkte = new ArrayList<Produkt>();

    produkte.add(new Produkt("Pulli", Money.of(50, "EUR"), 1));

    Bestellung bestellung = new Bestellung(1, produkte);
    bestellung.setVersandOption(Versand.STANDARD);

    double rabatt = 0.50;

    assertThat(bestellung.reduzierterPreis(rabatt).isEqualTo(Money.of(25, "EUR")));
  }

  @Test
  @DisplayName("Testet ob standardversandkosten berrechnet werden")
  public void test_4(){
    List<Produkt> produkte = List.of(new Produkt("Pulli", Money.of(50, "EUR"), 1));

    Bestellung bestellung = new Bestellung(1, produkte);
    bestellung.setVersandOption(Versand.STANDARD);

    assertThat(bestellung.preisMitVersand()).isEqualTo(Money.of(50 + 2.5, "EUR"));
  }

  @Test
  @DisplayName("Testet ob expressversandkosten berrechnet werden")
  public void test_5(){
    List<Produkt> produkte = List.of(new Produkt("Pulli", Money.of(50, "EUR"), 2));

    Bestellung bestellung = new Bestellung(1, produkte);
    bestellung.setVersandOption(Versand.EXPRESS);

    assertThat(bestellung.preisMitVersand()).isEqualTo(Money.of(50 + 50 + 20, "EUR"));
  }

  @Test
  @DisplayName("Bestellungen mit gleicher bestellnummer sind gleich")
  public void test_6(){
    Bestellung b1 = new Bestellung(1, Collections.emptyList());
    Bestellung b2 = new Bestellung(1, List.of(new Produkt("Katze", Money.of(999, "EUR"), 2)));

    assertThat(b1).isEqualTo(b2);
  }

  @Test
  @DisplayName("Bestellungen mit unterschiedlicher bestellnummer sind unterschiedlich")
  public void test_7(){
    Bestellung b1 = new Bestellung(1, Collections.emptyList());
    Bestellung b2 = new Bestellung(2, Collections.emptyList());

    assertThat(b1).isNotEqualTo(b2);
  }
}
