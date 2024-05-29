package vermeg.com.applicationbancaire.Services.IMP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vermeg.com.applicationbancaire.Models.UserModel;
import vermeg.com.applicationbancaire.Repositories.UserRepo;
import vermeg.com.applicationbancaire.Services.UserService;

import java.util.List;

@Service
public class UserServiceIMP implements UserService {
    @Autowired
    UserRepo Ur;

    public UserModel Create(UserModel A) {
        return Ur.save(A);
    }

    public UserModel Update(UserModel A) {
        return Ur.save(A);
    }

    public UserModel Getone(Long Id) {
        return Ur.findById(Id).orElse(null);
    }

    public List<UserModel> Totale() {
        return Ur.findAll();
    }

    public void Delate(Long Id) {
        Ur.deleteById(Id);
    }
}