package vermeg.com.applicationbancaire.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vermeg.com.applicationbancaire.Models.CGUModel;

@Repository
public interface CGURepo extends JpaRepository <CGUModel,Long> {
}
