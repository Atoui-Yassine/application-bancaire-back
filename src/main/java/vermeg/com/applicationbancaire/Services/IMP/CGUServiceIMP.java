package vermeg.com.applicationbancaire.Services.IMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vermeg.com.applicationbancaire.Models.CGUModel;
import vermeg.com.applicationbancaire.Repositories.CGURepo;
import vermeg.com.applicationbancaire.Services.CGUService;

@Service
public class CGUServiceIMP  implements CGUService {
    @Autowired
    CGURepo cguRepo;
    public CGUModel Create(CGUModel N) {
        return cguRepo.save(N);}
    public CGUModel Update (CGUModel N){ return cguRepo.save(N);}
    public CGUModel Getone (Long abc){return cguRepo.findById(abc).orElse(null);}
    public void Delate(Long Id){cguRepo.deleteById(Id);}
}
