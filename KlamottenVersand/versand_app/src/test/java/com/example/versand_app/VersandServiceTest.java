package com.example.versand_app;

import com.example.versandt_core.Bestellung;
import com.example.versandt_core.Produkt;
import com.example.versandt_core.Versand;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.example.versandt_core.Versand.EXPRESS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class VersandServiceTest {

  @Test
  @DisplayName("Soll Bestellung aus der Datenbank laden und daraus ein Bestellungsobjekt machen")
  void test_01(){
    List<Produkt> produkte = List.of(new Produkt("Hose", Money.of(30, "EUR"), 1));
    Bestellung bestellung = new Bestellung(1, produkte);
    bestellung.setVersandOption(Versand.STANDARD);

    BestellungRepository repository = mock(BestellungRepository.class);
    when(repository.findBestellungById(15)).thenReturn(bestellung);

    VersandService versandService = new VersandService(repository, mock(FulfillmentSystem.class));

    Bestellung gesuchteBestellung = versandService.getBestellung(15, EXPRESS);

    assertThat(gesuchteBestellung.getVersandOption()).isEqualTo(EXPRESS);
    assertThat(gesuchteBestellung).isEqualTo(bestellung);
  }

  @Test
  @DisplayName("Bei BESTELLEN soll eine bestellung abgeschlossen werden")
  void test_02(){
    BestellungRepository repository = mock(BestellungRepository.class);
    Bestellung bestellung = new Bestellung(15, Collections.emptyList());
    bestellung.setVersandOption(EXPRESS);
    when(repository.findBestellungById(anyInt())).thenReturn(bestellung);

    FulfillmentSystem fulfillment = mock(FulfillmentSystem.class);
    VersandService versandService = new VersandService(repository, fulfillment);

    versandService.bestellungVerschicken(15, Versand.EXPRESS);

    verify(fulfillment).fulfill(new Bestellung(15, Collections.emptyList()));
  }
}
