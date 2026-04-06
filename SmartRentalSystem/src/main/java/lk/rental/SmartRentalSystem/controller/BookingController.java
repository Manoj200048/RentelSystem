package lk.rental.SmartRentalSystem.controller;

import lk.rental.SmartRentalSystem.controller.request.CreateBookingRequest;
import lk.rental.SmartRentalSystem.controller.response.PaginatedResponse;
import lk.rental.SmartRentalSystem.controller.response.ViewAllBooking;
import lk.rental.SmartRentalSystem.exception.BookingNotFoundException;
import lk.rental.SmartRentalSystem.exception.ItemNotFoundException;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;
import lk.rental.SmartRentalSystem.model.Booking;
import lk.rental.SmartRentalSystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(headers = "X-Api-Version=v1")
    public PaginatedResponse<ViewAllBooking> getAll(@RequestParam("page")Integer page,@RequestParam("size")Integer size){
        Page<Booking> bookingPage = bookingService.findAll(page,size);

        List<ViewAllBooking> content = bookingPage.getContent().stream()
                .map(booking -> ViewAllBooking.builder()
                        .startDate(booking.getStartDate())
                        .endDate(booking.getEndDate())
                        .bookingPaymentStatus(booking.getBookingPaymentStatus())
                        .totalDays(booking.getTotalDays())
                        .totalPrice(booking.getTotalPrice())
                        .createdAt(booking.getCreatedAt())
                        .updatedAt(booking.getUpdatedAt())
                        .status(booking.getStatus())
                        .build())
                .collect(Collectors.toList());

        return PaginatedResponse.<ViewAllBooking>builder()
                .content(content)
                .page(bookingPage.getNumber())
                .size(bookingPage.getSize())
                .totalElements(bookingPage.getTotalElements())
                .totalPages(bookingPage.getTotalPages())
                .last(bookingPage.isLast())
                .build();


    }
}