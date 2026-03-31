package lk.rental.SmartRentalSystem.service.impl;

import lk.rental.SmartRentalSystem.controller.request.CreateBookingRequest;
import lk.rental.SmartRentalSystem.exception.BookingNotFoundException;
import lk.rental.SmartRentalSystem.exception.ItemNotFoundException;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;
import lk.rental.SmartRentalSystem.model.Booking;
import lk.rental.SmartRentalSystem.model.Item;
import lk.rental.SmartRentalSystem.model.User;
import lk.rental.SmartRentalSystem.model.enums.BookingPaymentStatus;
import lk.rental.SmartRentalSystem.model.enums.BookingStatus;
import lk.rental.SmartRentalSystem.repository.BookingRepository;
import lk.rental.SmartRentalSystem.repository.ItemRepository;
import lk.rental.SmartRentalSystem.repository.UserRepository;
import lk.rental.SmartRentalSystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public void add(Long cid, Long id, CreateBookingRequest createBookingRequest) throws UserNotFoundException,ItemNotFoundException {
        User user = userRepository.findById(cid).orElseThrow(
                ()->new UserNotFoundException("User Not Found..!")
        );

        Item item = itemRepository.findById(id).orElseThrow(
                ()->new ItemNotFoundException("Item Not Found..!")
        );

        Booking booking = new Booking();

        //check already booked selected item
        List<Booking> conflicts = bookingRepository.findConflictingBookings(
                item.getId(),
                createBookingRequest.getStartDate(),
                createBookingRequest.getEndDate()
        );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Item is already booked for selected dates");
        }

        booking.setCustomer(user);
        booking.setItem(item);
        booking.setStartDate(createBookingRequest.getStartDate());
        booking.setEndDate(createBookingRequest.getEndDate());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setBookingPaymentStatus(BookingPaymentStatus.UNPAID);

        //validate date
        if (createBookingRequest.getEndDate().isBefore(createBookingRequest.getStartDate())) {
            throw new RuntimeException("End date cannot be before start date");
        }

        //calculate total day
        long totalDays = ChronoUnit.DAYS.between(
                createBookingRequest.getStartDate(),
                createBookingRequest.getEndDate()
        ) + 1;

        booking.setTotalDays((int)totalDays);

        //calculate total price
        double totalPrice= totalDays* item.getPricePerDay();
        booking.setTotalPrice(totalPrice);

        booking.setStatus(BookingStatus.PENDING);

        bookingRepository.save(booking);



    }

    @Override
    public void cancel(Long id) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(id).orElseThrow(
                ()->new BookingNotFoundException("Booking Not Found..!")
        );

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }
}
