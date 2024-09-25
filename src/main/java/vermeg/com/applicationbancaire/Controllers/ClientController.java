package vermeg.com.applicationbancaire.Controllers;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import vermeg.com.applicationbancaire.Models.AdminModel;
import vermeg.com.applicationbancaire.payload.request.SignupRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vermeg.com.applicationbancaire.Models.ClientModel;
import vermeg.com.applicationbancaire.Repositories.ClientRepo;
import vermeg.com.applicationbancaire.Services.IMP.ClientServiceIMP;
import vermeg.com.applicationbancaire.payload.response.MessageResponse;
import vermeg.com.applicationbancaire.utils.utils.StorageService;

@RestController
@RequestMapping("client")
@CrossOrigin(origins = "*", maxAge = 3600)

public class ClientController {
    @Autowired
    ClientServiceIMP clientServiceIMP;
    @Autowired
    StorageService storageService;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private JavaMailSender javaMailSender;
    //@PostMapping(path="/createx" , consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
   // public ClientModel createOfAdmin(ClientModel  admin, @RequestPart MultipartFile file) {
        //String name = storageService.store(file);
      //  admin.setPhoto(name);
       // return clientServiceIMP.Create(admin);
    //}

        @PostMapping(path = "/create",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
        ResponseEntity<?> creation( SignupRequest sn, @RequestParam("file") MultipartFile file) throws MessagingException
        {
            if (clientRepo.existsByUsername(sn.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Client is already taken!"));
            }
            if (clientRepo.existsByEmail(sn.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
            }
            {  //apel au construcetur client
                ClientModel a = new ClientModel(
                        sn.getUsername(),
                        sn.getEmail(),
                        sn.getPhone(),
                        null,
                        encoder.encode(sn.getPassword()),
                        sn.getRole(),
                        sn.getCodeclient(),
                        sn.getCivilité(),
                        sn.getVilledenaissance(),
                        sn.getCodepostaledenaissance(),
                        sn.getPaysdenaissance(),
                        sn.getNationnalité()



                );
                // ajout de photo
                String namephoto = storageService.store(file);
                a.setPhoto(namephoto);
                // creation de admin
                clientServiceIMP.Create(a);
                String from = "yassin2016.attoui@gmail.com";
                String to = a.getEmail();
                String subject = "confirmation";
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(message);
                messageHelper.setSubject(subject);
                messageHelper.setTo(to);
                messageHelper.setFrom(from);
                messageHelper.setText("<HTML><body>" +
                        " <a href=\"http://localhost:8085/client/confirm?email="
                        + sn.getEmail() + "\">VERIFY</a></body></HTML>", true);
                javaMailSender.send(message);//envoyer email
                return ResponseEntity.ok().body("client is created");
            }

        }


        @PutMapping(path="/updateprofilepicture/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
        public ClientModel updatepict(@PathVariable Long id, ClientModel A,@RequestPart MultipartFile file){

            A.setId(id);
            ClientModel ancien = clientServiceIMP.Getone(id);
            if (A.getUsername() == null) {
                A.setUsername(ancien.getUsername());
            }

            if (A.getEmail() == null) {
                A.setEmail(ancien.getEmail());
            }


            if (A.getRole() == null) {
                A.setRole(ancien.getRole());
            }


            if (A.getCodeclient() == null) {
                A.setCodeclient(ancien.getCodeclient());
            }
            if (A.getPhone() == null) {
                A.setPhone(ancien.getPhone());
            }
            if (A.getCivilité() == null) {
                A.setCivilité(ancien.getCivilité());
            }
            if (A.getNationnalité() == null) {
                A.setNationnalité(ancien.getNationnalité());
            }
            if (A.getVilledenaissance() == null) {
                A.setVilledenaissance(ancien.getVilledenaissance());
            }
            if (A.getCodepostaledenaissance() == null) {
                A.setCodepostaledenaissance(ancien.getCodepostaledenaissance());
            }

            if (A.getPassword() == null) {
                A.setPassword(ancien.getPassword());
            } else {
                A.setPassword(encoder.encode(A.getPassword()));
            }

            if (A.getPaysdenaissance()==null){
                A.setPaysdenaissance (ancien.getPaysdenaissance());}
            if (file.isEmpty()) {
                A.setPhoto(ancien.getPhoto());
            } else {
                String name = storageService.store(file);
                A.setPhoto(name);
            }
            return clientRepo.save(A);
        }

    @PutMapping(path = "/update/{id}")

    public ClientModel Update(@PathVariable Long id, ClientModel A)throws MessagingException {
        A.setId(id);
        ClientModel ancien = clientServiceIMP.Getone(id);
        if (A.getUsername() == null) {
            A.setUsername(ancien.getUsername());
        }

        if (A.getEmail() == null) {
            A.setEmail(ancien.getEmail());
        }


        if (A.getRole() == null) {
            A.setRole(ancien.getRole());
        }


        if (A.getCodeclient() == null) {
            A.setCodeclient(ancien.getCodeclient());
        }
        if (A.getPhone() == null) {
            A.setPhone(ancien.getPhone());
        }
        if (A.getCivilité() == null) {
            A.setCivilité(ancien.getCivilité());
        }
        if (A.getNationnalité() == null) {
            A.setNationnalité(ancien.getNationnalité());
        }
        if (A.getVilledenaissance() == null) {
            A.setVilledenaissance(ancien.getVilledenaissance());
        }
        if (A.getCodepostaledenaissance() == null) {
            A.setCodepostaledenaissance(ancien.getCodepostaledenaissance());
        }

        if (A.getPassword() == null) {
            A.setPassword(ancien.getPassword());
        } else {
            A.setPassword(encoder.encode(A.getPassword()));
        }

        if (A.getPaysdenaissance()==null){
            A.setPaysdenaissance (ancien.getPaysdenaissance());}


        if (A.getPhoto()==null) {
                A.setPhoto(ancien.getPhoto());
            }


        clientRepo.save(A);
        String from = "test.mail.com";
        String to = A.getEmail();
        String subject = "le Compte a été modifé avec succés";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setSubject(subject);
        messageHelper.setTo(to);
        messageHelper.setFrom(from);
        messageHelper.setText("<HTML>l<body>" +
                " <a href=\"http://localhost:8085/client/confirm?email="
                +A.getEmail() + "\">VERIFY</a></body></HTML>", true);
        javaMailSender.send(message);//envoyer email
        return (A);

    }


    @GetMapping("/findClientById/{id}")
    public ClientModel getOneAdmin(@PathVariable Long id) {

        return clientServiceIMP .Getone(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        clientServiceIMP.Delate(id);
    }
    @GetMapping("/confirm")
    public ResponseEntity<?> confirm( @RequestParam String email) {
        // Create new user's account
        ClientModel user = clientRepo.findFirstByEmail(email);
        if(user != null){
            user.setConfirm(true);
            clientRepo.save(user);
            return ResponseEntity.ok(new MessageResponse("client is confirmed"));
        }
        return ResponseEntity.ok(new MessageResponse("client is Not confirmed"));
    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody

    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}

