package vermeg.com.applicationbancaire.Controllers;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import vermeg.com.applicationbancaire.Models.RefreshToken;
import vermeg.com.applicationbancaire.Models.UserModel;
import vermeg.com.applicationbancaire.Repositories.UserRepo;
import vermeg.com.applicationbancaire.payload.request.LoginRequest;
import vermeg.com.applicationbancaire.payload.response.JwtResponse;
import vermeg.com.applicationbancaire.payload.response.MessageResponse;
import vermeg.com.applicationbancaire.security.jwt.JwtUtils;
import vermeg.com.applicationbancaire.security.services.RefreshTokenService;
import vermeg.com.applicationbancaire.security.services.UserDetailsImpl;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Optional;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//@SecurityRequirement(name = "Authorization")
//@EnableSwagger2

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Auth")
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class Authentification {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    UserRepo utilisateurrepot;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JavaMailSender javaMailSender;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
System.out.println("step1");
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));//fonction de java tekhou en parametre username w password//
//optinal tnajem tkoun null
        System.out.println("step2");
        Optional<UserModel> u=utilisateurrepot.findByUsername(loginRequest.getUsername());
        System.out.println("step3");
        if (u.get().isConfirm()==true) {
            SecurityContextHolder.getContext().setAuthentication(authentication);//besh yekhou l'authentification li snaaha en parametre//
            System.out.println("step4");
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();//ykharej les elements princip ta user w yhotha fl user details//
            System.out.println("step5");
            String jwt = jwtUtils.generateJwtToken(userDetails);//jwt json(structure de donne: format teeou yaany) web token(acces)
            System.out.println("step6");

         RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
//retour de fonction
            return ResponseEntity.ok(new JwtResponse(jwt,"Bearer",
                    refreshToken.getToken(),
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.getRole()));

        }else {
            throw new RuntimeException("user not confirmed");
        }

    }





    @SecurityRequirement(name = "bearerAuth")

    @GetMapping("/signout")

    public ResponseEntity<?> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }









    @PostMapping("/forgetPassword")
    public HashMap<String, String> resetPassword(@RequestParam("email") String email) throws MessagingException {
        HashMap<String, String> response = new HashMap<>();
        UserModel existingUser = utilisateurrepot.findFirstByEmail(email);

        if (existingUser == null) {
            response.put("user", "User not found");
            return response;
        }

        // Générer un code aléatoire de 6 caractères
        String code = generateRandomCode(6);
        existingUser.setPasswordResetToken(code);

        // Envoyer un e-mail à l'utilisateur avec le nouveau mot de passe
        String from = "admin@admin.fr";
        String to = existingUser.getEmail();
        MimeMessage message1 = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message1);
        helper.setSubject("Reset password!");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText("Votre code est : " + existingUser.getPasswordResetToken(), true);
        javaMailSender.send(message1);

        utilisateurrepot.save(existingUser);
        response.put("user", "User found, check your email");
        return response;
    }

    private String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }










    @PostMapping("/savePassword/{passwordResetToken}")
    public HashMap<String,String> savepassword(@PathVariable String passwordResetToken, String newpassword)
    {   HashMap messaj = new HashMap();
        UserModel existuser = utilisateurrepot.findByPasswordResetToken(passwordResetToken);
        if(existuser != null)
        {
            existuser.setId(existuser.getId());
            existuser.setPassword(new BCryptPasswordEncoder().encode(newpassword));
            existuser.setPasswordResetToken(null);//à mettre une seule fois le code
            utilisateurrepot.save(existuser); // pour sauveharder les nouvelles donées
            messaj.put("resetpassword","password has been chaged");
            return  messaj;
        }
        else {
            messaj.put("resetpassword","failed");
            return messaj;
        }
    }
}







