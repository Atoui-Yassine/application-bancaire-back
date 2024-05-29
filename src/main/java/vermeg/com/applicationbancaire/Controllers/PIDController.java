package vermeg.com.applicationbancaire.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vermeg.com.applicationbancaire.Models.PIDModel;
import vermeg.com.applicationbancaire.Services.IMP.PIDServiceIMP;
import vermeg.com.applicationbancaire.utils.utils.StorageService;

@RestController
@RequestMapping("PID")
public class PIDController {
    @Autowired
    PIDServiceIMP pidServiceIMP;
    @Autowired
    StorageService storageService;

    @PostMapping(path = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public PIDModel create(PIDModel pid) {
        return pidServiceIMP.Create(pid);
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        pidServiceIMP.Delate(id);
    }

    @PutMapping(path = "/update/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})

    public PIDModel Update(@PathVariable Long id, PIDModel u, @RequestPart MultipartFile file) {
        u.setId(id);
        PIDModel ancien = pidServiceIMP.Getone(id);
        if (u.getNumcin() == null) {
            u.setNumcin(ancien.getNumcin());
        }

        if (u.getDatededélivrance() == null) {
            u.setDatededélivrance(ancien.getDatededélivrance());
        }


        if (u.getDatedenaissance() == null) {
            u.setDatedenaissance(ancien.getDatedenaissance());
        }

        if (u.getDatedevalidité() == null) {
            u.setDatedevalidité(ancien.getDatedevalidité());
        }

        if (u.getLieudedélivrance () == null) {
            u.setLieudedélivrance (ancien.getLieudedélivrance ());
        }

        return pidServiceIMP.Update(u);
    }
}