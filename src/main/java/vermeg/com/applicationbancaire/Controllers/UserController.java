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
import vermeg.com.applicationbancaire.Models.UserModel;
import vermeg.com.applicationbancaire.Repositories.UserRepo;
import vermeg.com.applicationbancaire.Services.IMP.AdminServiceIMP;
import vermeg.com.applicationbancaire.Services.IMP.UserServiceIMP;
import vermeg.com.applicationbancaire.payload.request.SignupRequest;
import vermeg.com.applicationbancaire.payload.response.MessageResponse;
import vermeg.com.applicationbancaire.utils.utils.StorageService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
@Autowired
    UserServiceIMP userServiceIMP;
@Autowired
StorageService storageService;
@Autowired
    UserRepo userRepo;
@Autowired
    PasswordEncoder encoder;
@Autowired
    private JavaMailSender javaMailSender;

    public UserModel Create(UserModel A) {
        return userServiceIMP.Create(A);

    }

    @PostMapping(path = "/create",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> creation(SignupRequest sn, @RequestParam("file") MultipartFile file) throws MessagingException {
        if (userRepo.existsByUsername(sn.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepo.existsByEmail(sn.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        {  //apel au construcetur admin
            UserModel a = new UserModel(
                    sn.getUsername(),
                    sn.getEmail(),
                    sn.getPhone(),
                    null,
                    encoder.encode(sn.getPassword()),
                    sn.getRole()


            );
            // ajout de photo
            String namephoto = storageService.store(file);
            a.setPhoto(namephoto);
            // creation de admin
            userServiceIMP.Create(a);
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
            return ResponseEntity.ok().body("user is created");
        }


    }







}




