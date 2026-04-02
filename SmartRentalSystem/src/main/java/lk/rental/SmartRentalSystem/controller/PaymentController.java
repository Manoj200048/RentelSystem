package lk.rental.SmartRentalSystem.controller;

import lk.rental.SmartRentalSystem.controller.request.CreatePaymentRequest;
import lk.rental.SmartRentalSystem.controller.response.ViewAllPayment;
import lk.rental.SmartRentalSystem.controller.response.ViewAllPaymentResponse;
import lk.rental.SmartRentalSystem.controller.response.ViewPaymentResponse;
import lk.rental.SmartRentalSystem.model.Payment;
import lk.rental.SmartRentalSystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payment")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private JsonMapper.Builder builder;

    @PostMapping(value = "/{customer-id}/{booking-id}",headers = "X-Api-Version=v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void pay(@PathVariable("customer-id")Long cid, @PathVariable("booking-id")Long bid, @RequestBody CreatePaymentRequest createPaymentRequest){
          paymentService.payPayment(cid,bid,createPaymentRequest);
    }

    @PutMapping(value = "/complete/{paymentId}/{bookingId}",headers = "X-Api-Version=v1")
    public void UpdatePaymentStatus(@PathVariable("paymentId") Long pid,@PathVariable("bookingId")Long bid){
        paymentService.updatePaymentStatus(pid,bid);
    }

    @GetMapping(value = "/{payment-id}",headers = "X-Api-Version=v1")
    public ViewPaymentResponse getById(@PathVariable("payment-id")Long id){
        Payment payment = paymentService.findById(id);

        return ViewPaymentResponse
                .builder()
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .transactionId(payment.getTransactionId())
                .paymentStatus(payment.getPaymentStatus())
                .createdAt(payment.getCreatedAt())
                .build();

    }

    @GetMapping(headers = "X-Api-Version= v1")
    public ViewAllPaymentResponse getAll(){
        List<Payment> paymentList = paymentService.findAll();

        List<ViewAllPayment> viewAllPayments = new ArrayList<>();

        for (Payment payment : paymentList){
            ViewAllPayment viewAllPayment = ViewAllPayment
                    .builder()
                    .amount(payment.getAmount())
                    .paymentMethod(payment.getPaymentMethod())
                    .transactionId(payment.getTransactionId())
                    .paymentStatus(payment.getPaymentStatus())
                    .createdAt(payment.getCreatedAt())
                    .build();

            viewAllPayments.add(viewAllPayment);
        }
        return ViewAllPaymentResponse
                .builder()
                .viewAllPaymentList(viewAllPayments)
                .build();

    }


}
