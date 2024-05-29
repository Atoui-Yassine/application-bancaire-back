package vermeg.com.applicationbancaire.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import vermeg.com.applicationbancaire.Models.AdminModel;
import vermeg.com.applicationbancaire.Models.Financement;
import vermeg.com.applicationbancaire.Services.IMP.FianancementServiceIMP;
import vermeg.com.applicationbancaire.utils.utils.StorageService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("financement")
public class FinancementController {
    private static final DateTimeFormatter CUSTOM_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    @Autowired
    FianancementServiceIMP fianancementServiceIMP;
    @Autowired
    StorageService storageService;

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


    @GetMapping("resultFiance")
    public Map<String, Double> resultfin(@RequestParam String startDateStr, @RequestParam int n, @RequestParam Double amount) {
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
        // Store the results in a database or any other storage mechanism // You can use a repository to save the results in a database // Example: resultRepository.saveAll(results); }
        // Method to retrieve the results for a specific date public Double getResultForDate(LocalDate date) { return results.getOrDefault(date, 0.0); } }
   return  results;

    }

}