package vermeg.com.applicationbancaire.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vermeg.com.applicationbancaire.Models.AdminModel;
import vermeg.com.applicationbancaire.Models.ClientModel;
import vermeg.com.applicationbancaire.Models.ContratModel;
import vermeg.com.applicationbancaire.Services.IMP.AdminServiceIMP;
import vermeg.com.applicationbancaire.Services.IMP.ContratServiceIMP;
import vermeg.com.applicationbancaire.utils.utils.StorageService;

@RestController
@RequestMapping("contrat")
public class contratController {
    @Autowired
    ContratServiceIMP contratServiceIMP;
    @Autowired
    StorageService storageService;

    @PostMapping( "/create")
    public ContratModel createOfAdmin(ContratModel contrat) {
        return contratServiceIMP.Create(contrat);

    }
    @PutMapping(path = "/update/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})

    public ContratModel Update(@PathVariable Long id, ContratModel B) {
        B.setId (id);
        ContratModel ancien = contratServiceIMP.Getone(id);

          if (B.getCodecontrat()==null){
                B.setCodecontrat(ancien.getCodecontrat());
          }
          if (B.getDatedebut()==null){
              B.setDatedebut(ancien.getDatedebut());
          }
          if (B.getDatefin()==null){
              B.setDatefin(ancien.getDatefin());
          }
          return contratServiceIMP.Update(B);
    }
    @GetMapping("/findContratById/{id}")
    public ContratModel getOne(@PathVariable Long id) {

        return contratServiceIMP .Getone(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        contratServiceIMP.Delate(id);
    }




















}
