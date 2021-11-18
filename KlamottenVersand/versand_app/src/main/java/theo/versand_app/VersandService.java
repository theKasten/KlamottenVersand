package theo.versand_app;

import theo.versandt_core.Bestellung;
import theo.versandt_core.Versand;

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
