package vermeg.com.applicationbancaire.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vermeg.com.applicationbancaire.Models.AdminModel;
import vermeg.com.applicationbancaire.Models.ClientModel;

import java.util.Optional;


@Repository
public interface ClientRepo extends JpaRepository<ClientModel,Long> {
    Optional<ClientModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    ClientModel findFirstByEmail (String email);
    ClientModel findByPasswordResetToken(String passwordResetToken);
}
