package theo.versand_app;

import theo.versandt_core.Bestellung;

public interface BestellungRepository {
  Bestellung findBestellungById(int i);
}
