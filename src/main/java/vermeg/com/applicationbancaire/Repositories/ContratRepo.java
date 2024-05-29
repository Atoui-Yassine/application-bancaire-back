package vermeg.com.applicationbancaire.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vermeg.com.applicationbancaire.Models.ContratModel;

@Repository
public interface ContratRepo extends JpaRepository <ContratModel,Long> {
}
