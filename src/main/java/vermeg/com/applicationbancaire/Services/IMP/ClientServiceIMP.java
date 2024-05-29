package vermeg.com.applicationbancaire.Services.IMP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vermeg.com.applicationbancaire.Models.AdminModel;
import vermeg.com.applicationbancaire.Models.ClientModel;
import vermeg.com.applicationbancaire.Repositories.ClientRepo;
import vermeg.com.applicationbancaire.Services.ClientService;

import java.util.List;

@Service
public class ClientServiceIMP implements ClientService {

@Autowired
    ClientRepo clientRepo;
    public ClientModel Create(ClientModel Y)
    {
        return clientRepo.save(Y);

    }

    public ClientModel Update (ClientModel Y)
    {
        return clientRepo.save(Y);

    }
    public ClientModel Getone (Long Id)
    {
        return clientRepo.findById(Id).orElse(null);
    }
    public List<ClientModel> Totale()
    {
    return clientRepo.findAll();
    }
    public void Delate(Long Id)
    {
        clientRepo.deleteById(Id);
    }

}
