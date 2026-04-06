package lk.rental.SmartRentalSystem.service;

import lk.rental.SmartRentalSystem.controller.request.CreateBookingRequest;
import lk.rental.SmartRentalSystem.exception.BookingNotFoundException;
import lk.rental.SmartRentalSystem.exception.ItemNotFoundException;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;
import lk.rental.SmartRentalSystem.model.Booking;
import org.springframework.data.domain.Page;

public interface BookingService {
    void add(Long cid, Long id, CreateBookingRequest createBookingRequest)throws UserNotFoundException, ItemNotFoundException;
    void cancel(Long id) throws BookingNotFoundException;
    Page<Booking>findAll(Integer page,Integer size);
}
