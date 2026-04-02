package lk.rental.SmartRentalSystem.controller;

import lk.rental.SmartRentalSystem.controller.request.CreatePaymentRequest;
import lk.rental.SmartRentalSystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/{customer-id}/{booking-id}",headers = "X-Api-Version=v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void pay(@PathVariable("customer-id")Long cid, @PathVariable("booking-id")Long bid, @RequestBody CreatePaymentRequest createPaymentRequest){
          paymentService.payPayment(cid,bid,createPaymentRequest);
    }

    @PutMapping(value = "/complete/{paymentId}/{bookingId}",headers = "X-Api-Version=v1")
    public void UpdatePaymentStatus(@PathVariable("paymentId") Long pid,@PathVariable("bookingId")Long bid){
        paymentService.updatePaymentStatus(pid,bid);
    }

}
