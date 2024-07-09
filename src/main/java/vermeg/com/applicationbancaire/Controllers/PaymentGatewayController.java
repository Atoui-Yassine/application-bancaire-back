package vermeg.com.applicationbancaire.Controllers;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/payment")
public class PaymentGatewayController {
    @Autowired
    private StripeClient stripeClient;
    @PostMapping("/create")
    public PaymentIntent createPayment(@RequestParam String currency,
                                       @RequestParam long amount,
                                       @RequestParam String paymentMethodToken) {
        try {
            return stripeClient.createPaymentIntent(currency, amount, paymentMethodToken);
        } catch (StripeException e) {
            // Handle exception (e.g., return error response)
            return null;
        }
    }
}
