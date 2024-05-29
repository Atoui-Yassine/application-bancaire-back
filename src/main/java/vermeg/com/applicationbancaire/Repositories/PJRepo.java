package vermeg.com.applicationbancaire.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vermeg.com.applicationbancaire.Models.PJModel;

@Repository
public interface PJRepo extends JpaRepository <PJModel,Long>{

}
