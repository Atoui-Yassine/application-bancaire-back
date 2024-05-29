package vermeg.com.applicationbancaire.Services;


import vermeg.com.applicationbancaire.Models.ContratModel;

import java.util.List;

public interface Contrat {
    ContratModel Create(ContratModel Z);
    ContratModel Update (ContratModel Z);
    ContratModel Getone (Long Id);
    List<  ContratModel> Totale();
    void Delate(Long Id);
}

