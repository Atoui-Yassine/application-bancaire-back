package vermeg.com.applicationbancaire.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vermeg.com.applicationbancaire.Models.PIDModel;
import vermeg.com.applicationbancaire.Models.PJModel;
import vermeg.com.applicationbancaire.Services.IMP.PIDServiceIMP;
import vermeg.com.applicationbancaire.Services.IMP.PJServiceIMP;
import vermeg.com.applicationbancaire.utils.utils.StorageService;

import java.util.List;


@RestController
@RequestMapping("PJ")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PJController {
    @Autowired
    StorageService storageService;
    @Autowired
    PJServiceIMP pjServiceIMP;

//    @GetMapping ("methodedepaiement/{num}")
//    public ResponseEntity<?> methodedepaiement(@PathVariable Long num)
//    {
//
//        return new ResponseEntity<>("methode");
//    }

    @PostMapping(path = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public PJModel create(PJModel pj ,
                          @RequestParam("relv1") MultipartFile relv1,
                          @RequestParam("relv2") MultipartFile relv2,
                          @RequestParam("relv3") MultipartFile relv3,
                          @RequestParam("relv4") MultipartFile relv4) {


        String n1=storageService.store(relv1);
        pj.setRelev1(n1);
System.out.println("1"+n1);
        String n2=storageService.store(relv2);
        pj.setRelev2(n2);
        System.out.println("2"+n2);
        String n3=storageService.store(relv3);
        pj.setRelev3(n3);
        System.out.println("3"+n3);
        String n4=storageService.store(relv4);
        pj.setRelev4(n4);
        System.out.println("4"+n4);
        return pjServiceIMP.Create(pj);
    }
    @GetMapping("/list")
    public List<PJModel> getAdmin() {return pjServiceIMP.Totale();}
@GetMapping("/files/{filename:.+}")
@ResponseBody
public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.loadFile(filename);
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);
}
}

