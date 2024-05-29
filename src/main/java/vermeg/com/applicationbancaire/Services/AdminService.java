package vermeg.com.applicationbancaire.Services;

import vermeg.com.applicationbancaire.Models.AdminModel;

import java.util.List;

public interface AdminService {
    AdminModel Create(AdminModel X);
    AdminModel Update (AdminModel X);
    AdminModel Getone (Long Id);
    List<AdminModel> Totale();
    void Delate(Long Id);
}
