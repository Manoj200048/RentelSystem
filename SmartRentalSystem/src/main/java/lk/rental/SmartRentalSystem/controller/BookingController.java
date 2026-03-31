package lk.rental.SmartRentalSystem.controller;

import lk.rental.SmartRentalSystem.controller.request.CreateBookingRequest;
import lk.rental.SmartRentalSystem.exception.BookingNotFoundException;
import lk.rental.SmartRentalSystem.exception.ItemNotFoundException;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;
import lk.rental.SmartRentalSystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Booking")
@Slf4j
@RequiredArgsConstructor
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping(value="/{customer-id}/{item-id}",headers = "X-Api-Version=v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBooking(@PathVariable("customer-id")Long cid , @PathVariable("item-id")Long id, @RequestBody CreateBookingRequest createBookingRequest)throws UserNotFoundException, ItemNotFoundException {
        bookingService.add(cid,id,createBookingRequest);
    }

    @PutMapping(value = "/{booking-id}",headers = "X-Api-Version=v1")
    public void cancelBooking(@PathVariable("booking-id")Long id)throws BookingNotFoundException {
        bookingService.cancel(id);
    }
}