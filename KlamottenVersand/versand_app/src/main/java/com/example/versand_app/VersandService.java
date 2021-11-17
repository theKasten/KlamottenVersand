package com.example.versand_app;

import com.example.versandt_core.Bestellung;
import com.example.versandt_core.Versand;

import java.util.Collections;

public class VersandService {
  BestellungRepository repository;
  FulfillmentSystem fulfillment;

  public VersandService(BestellungRepository repository, FulfillmentSystem fulfillment) {
    this.repository = repository;
    this.fulfillment = fulfillment;
  }

  public Bestellung getBestellung(int nr, Versand versandOption) {
    Bestellung bestellung = repository.findBestellungById(nr);
    bestellung.setVersandOption(versandOption);
    return bestellung;
  }

  public void bestellungVerschicken(int nr, Versand versandOption) {
    Bestellung bestellung = getBestellung(nr, versandOption);
    fulfillment.fulfill(bestellung);
  }
}
