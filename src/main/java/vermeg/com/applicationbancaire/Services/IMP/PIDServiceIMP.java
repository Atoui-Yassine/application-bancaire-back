package vermeg.com.applicationbancaire.Services.IMP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vermeg.com.applicationbancaire.Models.PIDModel;
import vermeg.com.applicationbancaire.Repositories.PIDRepo;
import vermeg.com.applicationbancaire.Services.PIDService;

import java.util.List;

@Service
public class PIDServiceIMP implements PIDService {
    @Autowired
    PIDRepo pidRepo;
    public PIDModel Create(PIDModel X){
        return pidRepo.save(X);
    }
    public PIDModel Update ( PIDModel X){
        return pidRepo.save(X);
    }
   public  PIDModel Getone (Long Id)
   {
       return pidRepo.findById(Id).orElse(null);
   }
    public List< PIDModel> Totale()
    {
        return pidRepo.findAll();
    }
    public void Delate(Long Id)
    {
        pidRepo.deleteById(Id);
    }

}
