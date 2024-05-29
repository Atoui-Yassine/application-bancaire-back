package vermeg.com.applicationbancaire.Services.IMP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vermeg.com.applicationbancaire.Models.ClientModel;
import vermeg.com.applicationbancaire.Models.ContratModel;
import vermeg.com.applicationbancaire.Repositories.ClientRepo;
import vermeg.com.applicationbancaire.Repositories.ContratRepo;
import vermeg.com.applicationbancaire.Services.Contrat;

import java.util.List;
@Service
public class ContratServiceIMP implements Contrat {
    @Autowired
    ContratRepo contratRepo;
   public ContratModel Create(ContratModel Z)
    {
        return contratRepo.save(Z);

    }
    public ContratModel Update (ContratModel Z)
    {
        return contratRepo.save(Z);

    }
   public ContratModel Getone (Long Id)
   {
       return contratRepo.findById(Id).orElse(null);
   }
    public List  <ContratModel> Totale()
    {
         return contratRepo.findAll();
    }
   public void Delate(Long Id)
   {
        contratRepo.deleteById(Id);
   }

}
