package vermeg.com.applicationbancaire.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vermeg.com.applicationbancaire.Models.AdminModel;
import vermeg.com.applicationbancaire.Models.CGUModel;
import vermeg.com.applicationbancaire.Services.CGUService;

@RestController
@RequestMapping ("CGU")
@CrossOrigin(origins = "*", maxAge = 3600)


public class CGUController {
    @Autowired
    CGUService cguServiceIMP;


    @PostMapping(path = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public CGUModel createOfAdmin(CGUModel C, @RequestPart MultipartFile file) {
        return cguServiceIMP.Create(C);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        cguServiceIMP.Delate(id);
    }

    @PutMapping(path = "/update/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})

    public CGUModel Update(@PathVariable Long id, CGUModel u, @RequestPart MultipartFile file) {
        u.setId(id);
        CGUModel ancien = cguServiceIMP.Getone(id);
        if (u.getDate() == null) {
            u.setDate(ancien.getDate());
        }

        if (u.getSignature() == null) {
            u.setSignature(ancien.getSignature());
        }


        if (u.getNombredepages() == null) {
            u.setNombredepages(ancien.getNombredepages());
        }


        if (u.getType() == null) {
            u.setType(ancien.getType());
        }
        return cguServiceIMP.Update(u);
    }
}












