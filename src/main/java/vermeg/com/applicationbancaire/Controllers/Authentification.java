package vermeg.com.applicationbancaire.Controllers;


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
import vermeg.com.applicationbancaire.Models.RefreshToken;
import vermeg.com.applicationbancaire.Models.UserModel;
import vermeg.com.applicationbancaire.Repositories.UserRepo;
import vermeg.com.applicationbancaire.payload.request.LoginRequest;
import vermeg.com.applicationbancaire.payload.response.JwtResponse;
import vermeg.com.applicationbancaire.payload.response.MessageResponse;
import vermeg.com.applicationbancaire.security.jwt.JwtUtils;
import vermeg.com.applicationbancaire.security.services.RefreshTokenService;
import vermeg.com.applicationbancaire.security.services.UserDetailsImpl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Auth")

public class Authentification {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    UserRepo utilisateurrepot;

    @Autowired
    AuthenticationManager authenticationManager;

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

    @GetMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }


}







