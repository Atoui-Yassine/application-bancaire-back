package vermeg.com.applicationbancaire.Services.IMP;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vermeg.com.applicationbancaire.Models.AdminModel;
import vermeg.com.applicationbancaire.Models.ContratModel;
import vermeg.com.applicationbancaire.Models.Financement;
import vermeg.com.applicationbancaire.Repositories.FinancementRepo;

import java.util.List;

@Service
public class FianancementServiceIMP {
    @Autowired
    FinancementRepo financementRepo;
    public Financement Create(Financement Z)
    {
        return financementRepo.save(Z);

    }
    public Financement Getone (Long Id)
    {
        return financementRepo.findById(Id).orElse(null);
    }

    public List<Financement> Totale()
    {
        return financementRepo.findAll();
    }

}
