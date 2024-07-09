package vermeg.com.applicationbancaire.Controllers;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import vermeg.com.applicationbancaire.Models.AdminModel;
import vermeg.com.applicationbancaire.Models.ClientModel;
import vermeg.com.applicationbancaire.Models.ContratModel;
import vermeg.com.applicationbancaire.Models.Financement;
import vermeg.com.applicationbancaire.Services.IMP.ClientServiceIMP;
import vermeg.com.applicationbancaire.Services.IMP.ContratServiceIMP;
import vermeg.com.applicationbancaire.Services.IMP.FianancementServiceIMP;
import vermeg.com.applicationbancaire.payload.request.SignupRequest;
import vermeg.com.applicationbancaire.payload.response.MessageResponse;
import vermeg.com.applicationbancaire.utils.utils.StorageService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@RestController
@RequestMapping("financement")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FinancementController {
    private static final DateTimeFormatter CUSTOM_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    @Autowired
    FianancementServiceIMP fianancementServiceIMP;
    @Autowired
    StorageService storageService;

    @Autowired
    ClientServiceIMP clientServiceIMP;
    @Autowired
    ContratServiceIMP contratServiceIMP;

    @PostMapping("/create")
    public Financement create(Financement financement) {
        return fianancementServiceIMP.Create(financement);

    }

    @GetMapping("/findfinancementById/{id}")
    public Financement getOne(@PathVariable Long id) {

        return fianancementServiceIMP.Getone(id);
    }

    @GetMapping("/list")
    public List<Financement> getAll() {
        return fianancementServiceIMP.Totale();
    }


    @GetMapping("/nextDates")
    public List<String> getNextThreeMonths(@RequestParam String startDateStr, @RequestParam int n) { // Parse the start date

//        LocalDate startDate  = LocalDate.parse(startDateStr);
//
//        List<String> nextThreeMonths = new ArrayList<>();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
//
//        for (int i = 1; i <= n; i++)
//        { LocalDate nextMonthDate = startDate.plusMonths(i);
//            nextThreeMonths.add(nextMonthDate.format(formatter)); }
//        return nextThreeMonths; }


        //  try {
        LocalDate parsedDate = LocalDate.parse(startDateStr, CUSTOM_DATE_FORMATTER);
        List<String> nextThreeMonths = new ArrayList<>();


        for (int i = 1; i <= n; i++) {
            LocalDate futureDate = parsedDate.plusMonths(i);
            nextThreeMonths.add(futureDate.format(CUSTOM_DATE_FORMATTER));
        }

        // return futureDate.format(CUSTOM_DATE_FORMATTER);
        return nextThreeMonths;

//        } catch (DateTimeParseException e) {
//            return "Invalid date format. Please use 'yyyy/MM/dd'.";
//        }


    }


    @GetMapping("resultFiance/{idclient}")
    public Map<String, Double> resultfin(@RequestParam String startDateStr, @RequestParam int n, @RequestParam Double amount, @PathVariable Long idclient) {
        Map<String, Double> results = new HashMap<>(); // Define the amount and number private double amount = 1000.0;
        // Define the specific dates in spring private final LocalDate[] specificDates = { LocalDate.of(2024, 5, 30), LocalDate.of(2024, 6, 15), LocalDate.of(2024, 6, 30)
        // Add more dates as needed }; // Scheduled task to perform the division operation @Scheduled(cron = "0 0 0 * * *") // Executes daily at midnight public void performDivision() {
        double result = amount / n;

        LocalDate parsedDate = LocalDate.parse(startDateStr, CUSTOM_DATE_FORMATTER);
        List<String> nextThreeMonths = new ArrayList<>();


        for (int i = 1; i <= n; i++) {
            LocalDate futureDate = parsedDate.plusMonths(i);
            nextThreeMonths.add(futureDate.format(CUSTOM_DATE_FORMATTER));
        }

        // return futureDate.format(CUSTOM_DATE_FORMATTER);
//return nextThreeMonths;
        for (String date : nextThreeMonths) {
            results.put(date, result);
        }

        /// Date d1=new Date(startDateStr);
        /// Date d2= new Date(nextThreeMonths.get(nextThreeMonths.size()));
        ///ContratModel c = new ContratModel( "000",d1,d2);
        /// contratServiceIMP.Create(c);
        // Store the results in a database or any other storage mechanism // You can use a repository to save the results in a database // Example: resultRepository.saveAll(results); }
        // Method to retrieve the results for a specific date public Double getResultForDate(LocalDate date) { return results.getOrDefault(date, 0.0); } }
        System.out.println("**********1********" + nextThreeMonths.get(1));
        System.out.println("**********2********" + nextThreeMonths.get(nextThreeMonths.size() - 1));
        Date d1 = new Date(nextThreeMonths.get(1));
        Date d2 = new Date(nextThreeMonths.get(nextThreeMonths.size() - 1));

        ContratModel c = new ContratModel("000", d1, d2);

        ClientModel C = clientServiceIMP.Getone(idclient);
        c.setClientModel(C);
        contratServiceIMP.Create(c);
        return results;

    }

    @GetMapping("propositiondefinancement/{id}")
    ResponseEntity<?> creation(@PathVariable Long id) {
        Financement f = fianancementServiceIMP.Getone(id);
        if (f.getApportpersonnel() == 0) {
            f.setMontantafinancer(f.getMontanttotal());
        } else
            f.setMontantafinancer(f.getMontanttotal() - f.getApportpersonnel());

        return ResponseEntity.ok().body("sucess to create propostion");
    }
}