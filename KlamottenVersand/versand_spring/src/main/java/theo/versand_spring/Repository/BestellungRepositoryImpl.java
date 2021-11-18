package theo.versand_spring.Repository;

import org.springframework.stereotype.Repository;
import theo.versand_app.BestellungRepository;
import theo.versandt_core.Bestellung;

@Repository
public class BestellungRepositoryImpl implements BestellungRepository {

  @Override
  public Bestellung findBestellungById(int i) {
    return null;
  }
}
