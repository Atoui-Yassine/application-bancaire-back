package vermeg.com.applicationbancaire.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vermeg.com.applicationbancaire.Models.PIDModel;

@Repository
public interface PIDRepo extends JpaRepository <PIDModel,Long> {

}
