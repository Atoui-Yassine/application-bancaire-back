package vermeg.com.applicationbancaire.Services;


import vermeg.com.applicationbancaire.Models.UserModel;

import java.util.List;

public interface UserService {
     UserModel Create(UserModel  A);
    UserModel Update (UserModel  A);
    UserModel Getone (Long Id);
    List<UserModel> Totale();
    void Delate(Long Id);
}
