package vermeg.com.applicationbancaire.Services.IMP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vermeg.com.applicationbancaire.Models.AdminModel;
import vermeg.com.applicationbancaire.Repositories.AdminRepo;
import vermeg.com.applicationbancaire.Services.AdminService;

import java.util.List;

@Service
public class AdminServiceIMP implements AdminService {


    @Autowired
    AdminRepo adminRepo;




    public AdminModel Create(AdminModel X){
        return adminRepo.save(X);
    }


    public AdminModel Update (AdminModel X){ return adminRepo.save(X);}
    public AdminModel Getone (Long abc){return adminRepo.findById(abc).orElse(null);}
    public List<AdminModel> Totale() {return  adminRepo.findAll();}
   public void Delate(Long Id){adminRepo.deleteById(Id);}
}
