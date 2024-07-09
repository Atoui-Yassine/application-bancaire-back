package vermeg.com.applicationbancaire.Controllers;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Component
public class StripeClient {

//    @Value("${stripe.api.key}")
//    private String stripeApiKey;
    StripeClient() {
      //  Stripe.apiKey = stripeApiKey;
        Stripe.apiKey = "sk_test_51PQX4tKtAaht4wcFAyI91WEP5uQzv33O8fplzgvXGlsRg6OqH8RqBShWBHY187n9GwclmiJDxrwxchkMCYyDBo9o00nuhSQkhn";
    }

    public PaymentIntent createPaymentIntent(String currency, long amount, String paymentMethodToken) throws StripeException {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amount)
                        .setCurrency(currency)
                        .setPaymentMethod(paymentMethodToken)
                        .setConfirm(true)
                        .build();

        return PaymentIntent.create(params);
    }

}

