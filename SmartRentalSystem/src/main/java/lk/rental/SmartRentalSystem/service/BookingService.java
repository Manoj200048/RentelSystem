package lk.rental.SmartRentalSystem.service;

import lk.rental.SmartRentalSystem.controller.request.CreateBookingRequest;
import lk.rental.SmartRentalSystem.exception.BookingNotFoundException;
import lk.rental.SmartRentalSystem.exception.ItemNotFoundException;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;

public interface BookingService {
    void add(Long cid, Long id, CreateBookingRequest createBookingRequest)throws UserNotFoundException, ItemNotFoundException;
    void cancel(Long id) throws BookingNotFoundException;
}
