package br.com.robbo.hrpayroll.resources;

import br.com.robbo.hrpayroll.entities.Payment;
import br.com.robbo.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

    @HystrixCommand(fallbackMethod = "getPaymentAlternative")
    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId,
                                              @PathVariable Integer days) {
        System.out.println("main ***************************** ");

        final Payment payment = paymentService.getPayment(workerId, days);

        return ResponseEntity.ok(payment);
    }

    public ResponseEntity<Payment> getPaymentAlternative(Long workerId, Integer days) {
        System.out.println("alternative **********************");
        final Payment payment = new Payment("Branm", 400.0, days);
        return ResponseEntity.ok(payment);
    }

}
