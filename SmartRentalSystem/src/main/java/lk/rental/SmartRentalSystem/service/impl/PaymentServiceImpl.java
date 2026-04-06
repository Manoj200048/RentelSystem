package lk.rental.SmartRentalSystem.service.impl;

import lk.rental.SmartRentalSystem.controller.request.CreatePaymentRequest;
import lk.rental.SmartRentalSystem.exception.BookingNotFoundException;
import lk.rental.SmartRentalSystem.exception.PaymentNotFoundException;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;
import lk.rental.SmartRentalSystem.model.Booking;
import lk.rental.SmartRentalSystem.model.Payment;
import lk.rental.SmartRentalSystem.model.User;
import lk.rental.SmartRentalSystem.model.enums.BookingPaymentStatus;
import lk.rental.SmartRentalSystem.model.enums.BookingStatus;
import lk.rental.SmartRentalSystem.model.enums.PaymentStatus;
import lk.rental.SmartRentalSystem.repository.BookingRepository;
import lk.rental.SmartRentalSystem.repository.PaymentRepository;
import lk.rental.SmartRentalSystem.repository.UserRepository;
import lk.rental.SmartRentalSystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;


    @Override
    @Transactional
    public void payPayment(Long cid, Long bid, CreatePaymentRequest createPaymentRequest) throws UserNotFoundException, BookingNotFoundException,RuntimeException {
        User user = userRepository.findById(cid).orElseThrow(
                ()->new UserNotFoundException("User Not Found...!")
        );

        Booking booking = bookingRepository.findById(bid).orElseThrow(
                ()->new BookingNotFoundException("Booking Not Found..!")
        );

        Payment payment = new Payment();

        if (booking.getPayment() != null &&
                booking.getPayment().getPaymentStatus() != PaymentStatus.FAILED) {

            throw new RuntimeException("Payment already in progress or completed");
        }

         payment.setCustomer(user);
         payment.setBooking(booking);
         payment.setAmount(createPaymentRequest.getAmount());

         if (booking.getTotalPrice().equals(payment.getAmount())) {

             payment.setPaymentMethod(createPaymentRequest.getPaymentMethod());
             payment.setPaymentStatus(PaymentStatus.PENDING);
             payment.setTransactionId(UUID.randomUUID().toString());
             payment.setCreatedAt(LocalDateTime.now());
         } else {
             throw new RuntimeException("Payment is invalid..!");
         }

         paymentRepository.save(payment);





    }

    @Override
    @Transactional
    public void updatePaymentStatus(Long pid,Long bid) throws PaymentNotFoundException,BookingNotFoundException {
        Payment payment = paymentRepository.findById(pid).orElseThrow(
                ()->new PaymentNotFoundException("Payment Not Found..!")
        );

        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);

        Booking booking = bookingRepository.findById(bid).orElseThrow(
                ()->new BookingNotFoundException("Booking Not Found..!")
        );

        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setBookingPaymentStatus(BookingPaymentStatus.PAID);
        booking.setUpdatedAt(LocalDateTime.now());
        bookingRepository.save(booking);

    }

    @Override
    public Payment findById(Long id) throws PaymentNotFoundException {
        return  paymentRepository.findById(id).orElseThrow(
                ()->new PaymentNotFoundException("Payment Not Found..!")
        );
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Page<Payment> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return paymentRepository.findAll(pageable);
    }


}
