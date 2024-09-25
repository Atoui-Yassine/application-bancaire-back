package vermeg.com.applicationbancaire.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vermeg.com.applicationbancaire.Models.*;
import vermeg.com.applicationbancaire.Repositories.ContratRepo;
import vermeg.com.applicationbancaire.Services.IMP.*;
import vermeg.com.applicationbancaire.utils.utils.StorageService;

@RestController
@RequestMapping("contrat")
@CrossOrigin(origins = "*", maxAge = 3600)
public class contratController {
    @Autowired
    ContratServiceIMP contratServiceIMP;
    @Autowired
    StorageService storageService;
@Autowired  ClientServiceIMP clientServiceIMP;
@Autowired PIDServiceIMP pidServiceIMP;
@Autowired CGUServiceIMP cguServiceIMP;
@Autowired PJServiceIMP pjServiceIMP;
@Autowired  ContratRepo contratRepo;
    @PostMapping( "/created/{idclient}")
    public ContratModel createOfAdmin(@RequestBody  ContratModel contrat,@PathVariable Long idclient) {
        System.out.println("Contrat reçu : " + contrat);
        ClientModel C=clientServiceIMP.Getone(idclient);
        contrat.setClientmap(C);
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

        return contratServiceIMP.Getone(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        contratServiceIMP.Delate(id);
    }



//Ajout piece identité (attcahement)
@PostMapping("/addPID/{IDcontrat}/{idpid}")
    public  ContratModel ajoutpiID(@PathVariable Long IDcontrat,@PathVariable Long idpid)
{
    ContratModel c =contratServiceIMP.Getone(IDcontrat);
    PIDModel pidm = pidServiceIMP.Getone(idpid);
    c.setPieceidentites(pidm);
    return contratRepo.save(c);
}


    //Ajout piece justificatifs (attachement)
    @PostMapping("/addPJD/{IDcontrat}/{idpjid}")
    public  ContratModel ajoutidpjid(@PathVariable Long IDcontrat,@PathVariable Long idpjid)
    {
        ContratModel c =contratServiceIMP.Getone(IDcontrat);
        PJModel just = pjServiceIMP.Getone(idpjid);
      c.setPiecejustfs(just);
        return contratRepo.save(c);
    }


    //Attachement des conditions generales
    @PostMapping("/attachCGU/{IDcontrat}/{idcgu}")
    public  ContratModel attachCGU(@PathVariable Long IDcontrat,@PathVariable Long idcgu)
    {
        ContratModel c =contratServiceIMP.Getone(IDcontrat);
        CGUModel cum = cguServiceIMP.Getone(idcgu);
        c.setConditiongenerales(cum);
        return contratRepo.save(c);
    }












}
