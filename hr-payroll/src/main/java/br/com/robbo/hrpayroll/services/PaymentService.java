package br.com.robbo.hrpayroll.services;

import br.com.robbo.hrpayroll.entities.Payment;
import br.com.robbo.hrpayroll.entities.Worker;
import br.com.robbo.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(long workerId, int days) {

        final Worker worker = workerFeignClient.findById(workerId).getBody();

        return new Payment(worker.getName(), worker.getDailyIncome(), days);


    }

}
