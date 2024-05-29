package vermeg.com.applicationbancaire.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import vermeg.com.applicationbancaire.Models.RefreshToken;
import vermeg.com.applicationbancaire.Models.UserModel;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(UserModel user);
}
