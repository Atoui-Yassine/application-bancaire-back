package vermeg.com.applicationbancaire.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vermeg.com.applicationbancaire.Models.AdminModel;
import vermeg.com.applicationbancaire.Models.UserModel;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository <AdminModel,Long> {
    Optional<AdminModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    AdminModel findFirstByEmail (String email);
    AdminModel findByPasswordResetToken(String passwordResetToken);
}
