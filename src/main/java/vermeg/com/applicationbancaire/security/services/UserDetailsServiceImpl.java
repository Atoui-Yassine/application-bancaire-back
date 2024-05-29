package vermeg.com.applicationbancaire.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vermeg.com.applicationbancaire.Models.UserModel;
import vermeg.com.applicationbancaire.Repositories.UserRepo;

//import com.securityEcommerce.models.User;
//import com.securityEcommerce.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 @Autowired
 UserRepo userrepo;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserModel user = userrepo.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    //return UserDetailsImpl.build(user);
      return UserDetailsImpl.build(user);
  }

}
