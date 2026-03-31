package lk.rental.SmartRentalSystem.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateBookingRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
