package vermeg.com.applicationbancaire.Services.IMP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vermeg.com.applicationbancaire.Models.PJModel;
import vermeg.com.applicationbancaire.Repositories.PJRepo;
import vermeg.com.applicationbancaire.Services.PJService;

import java.util.List;

@Service
public class PJServiceIMP implements PJService {
    @Autowired
    PJRepo pjRepo;
    public PJModel Create(PJModel A){
        return pjRepo.save(A);
    }
    public PJModel Update ( PJModel A){
        return pjRepo.save(A);
    }
    public  PJModel Getone (Long Id)
    {
        return pjRepo.findById(Id).orElse(null);
    }
    public List< PJModel> Totale()
    {
        return pjRepo.findAll();
    }
    public void Delate(Long Id)
    {
        pjRepo.deleteById(Id);
    }

}



