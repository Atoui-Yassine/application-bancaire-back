package vermeg.com.applicationbancaire.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vermeg.com.applicationbancaire.Models.ContratModel;
import vermeg.com.applicationbancaire.Models.Financement;

@Repository
public interface FinancementRepo extends JpaRepository<Financement,Long> {
}
