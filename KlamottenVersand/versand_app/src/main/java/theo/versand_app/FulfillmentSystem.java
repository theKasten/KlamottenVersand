package theo.versand_app;

import theo.versandt_core.Bestellung;

public interface FulfillmentSystem {
  void fulfill(Bestellung bestellung);
}
