package com.example.versand_app;

import com.example.versandt_core.Bestellung;

public interface BestellungRepository {
  Bestellung findBestellungById(int i);
}
