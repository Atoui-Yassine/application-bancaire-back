package vermeg.com.applicationbancaire.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vermeg.com.applicationbancaire.Models.UserModel;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository <UserModel,Long> {

    Optional<UserModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    UserModel findFirstByEmail (String email);
    UserModel findByPasswordResetToken(String passwordResetToken);
    public interface UserRepository extends JpaRepository<UserModel, Long> {
        UserModel findFirstByEmail(String email);
    }
}
