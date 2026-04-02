package lk.rental.SmartRentalSystem.service;

import lk.rental.SmartRentalSystem.controller.request.CreatePaymentRequest;
import lk.rental.SmartRentalSystem.exception.BookingNotFoundException;
import lk.rental.SmartRentalSystem.exception.PaymentNotFoundException;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;
import lk.rental.SmartRentalSystem.model.Payment;

import java.util.List;

public interface PaymentService {
    void payPayment(Long cid, Long bid, CreatePaymentRequest createPaymentRequest) throws UserNotFoundException, BookingNotFoundException;
    void updatePaymentStatus(Long pid,Long bid) throws PaymentNotFoundException,BookingNotFoundException;
    Payment findById(Long id) throws PaymentNotFoundException;
    List<Payment> findAll();
}
