package vermeg.com.applicationbancaire.Services;


import vermeg.com.applicationbancaire.Models.Financement;

import java.util.List;

public interface FinancementService {
    Financement Create(Financement Z);
    Financement Getone(Financement Z);
    List <Financement> Totale();
}
