package com.example.versand_app;

import com.example.versandt_core.Bestellung;

public interface FulfillmentSystem {
  void fulfill(Bestellung bestellung);
}
