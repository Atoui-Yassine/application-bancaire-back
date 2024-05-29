package vermeg.com.applicationbancaire.Services;


import vermeg.com.applicationbancaire.Models.ClientModel;

import java.util.List;

public interface ClientService {
    ClientModel Create(ClientModel Y);
    ClientModel Update (ClientModel Y);
    ClientModel Getone (Long Id);
    List<ClientModel> Totale();
    void Delate(Long Id);
}

