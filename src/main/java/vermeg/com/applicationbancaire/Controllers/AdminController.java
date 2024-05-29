package vermeg.com.applicationbancaire.Controllers;


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
import vermeg.com.applicationbancaire.Models.AdminModel;
import vermeg.com.applicationbancaire.Repositories.AdminRepo;
import vermeg.com.applicationbancaire.Repositories.UserRepo;
import vermeg.com.applicationbancaire.Services.IMP.AdminServiceIMP;
import vermeg.com.applicationbancaire.payload.request.SignupRequest;
import vermeg.com.applicationbancaire.payload.response.MessageResponse;
import vermeg.com.applicationbancaire.utils.utils.StorageService;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminServiceIMP adminServiceIMP;
    @Autowired
    StorageService storageService;
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private JavaMailSender javaMailSender;
  //  @PostMapping(path = "/createx", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  ///  public AdminModel createOfAdmin(AdminModel admin, @RequestPart MultipartFile file) {
       // String name = storageService.store(file);
      //  admin.setPhoto(name);
       // return adminServiceIMP.Create(admin);

  //  }
    @PostMapping(path = "/create",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> creation(SignupRequest sn, @RequestParam("file") MultipartFile file) throws MessagingException {
        if (adminRepo.existsByUsername(sn.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (adminRepo.existsByEmail(sn.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        {  //apel au construcetur admin
            AdminModel a = new AdminModel(
                    sn.getUsername(),
                    sn.getEmail(),
                    sn.getPhone(),
                    null,
                    encoder.encode(sn.getPassword()),
                    sn.getRole(),
                    sn.getCodeAdmin()

            );
            // ajout de photo
            String namephoto = storageService.store(file);
            a.setPhoto(namephoto);
            // creation de admin
            adminServiceIMP.Create(a);
            String from = "test.mail.com";
            String to = a.getEmail();
            String subject = "confirmation";
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setSubject(subject);
            messageHelper.setTo(to);
            messageHelper.setFrom(from);
            messageHelper.setText("<HTML><body>" +
                    " <a href=\"http://localhost:8085/admin/confirm?email="
                    + sn.getEmail() + "\">VERIFY</a></body></HTML>", true);
            javaMailSender.send(message);//envoyer email
            return ResponseEntity.ok().body("admin is created");
        }

    }




    @GetMapping("/list")
    public List<AdminModel> getAdmin() {
        return adminServiceIMP.Totale();
    }

    @GetMapping("/findAdminById/{id}")
    public AdminModel getOneAdmin(@PathVariable Long id) {

        return adminServiceIMP.Getone(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        adminServiceIMP.Delate(id);
    }

    @PutMapping(path = "/update/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})

    public AdminModel Update(@PathVariable Long id, AdminModel u, @RequestPart MultipartFile file) {
        u.setId(id);
        AdminModel ancien = adminServiceIMP.Getone(id);
        if (u.getUsername() == null) {
            u.setUsername(ancien.getUsername());
        }

        if (u.getEmail() == null) {
            u.setEmail(ancien.getEmail());
        }


        if (u.getRole() == null) {
            u.setRole(ancien.getRole());
        }


        if (u.getCodeAdmin() == null) {
            u.setCodeAdmin(ancien.getCodeAdmin());
        }

        String name = storageService.store(file);
        u.setPhoto(name);
        return adminServiceIMP.Update(u);
    }


    @GetMapping("/detail/{id}")
    AdminModel détails(@PathVariable Long id) {
        return adminServiceIMP.Getone(id);

    }
    @GetMapping("/confirm")
    public ResponseEntity<?> confirm( @RequestParam String email) {
        // Create new user's account
        AdminModel user = adminRepo.findFirstByEmail(email);
        if(user != null){
            user.setConfirm(true);
            adminRepo.save(user);
            return ResponseEntity.ok(new MessageResponse("Admin is confirmed"));
        }
        return ResponseEntity.ok(new MessageResponse("Admin is Not confirmed"));
    }
}