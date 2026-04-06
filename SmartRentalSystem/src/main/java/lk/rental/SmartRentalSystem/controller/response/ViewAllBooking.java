package lk.rental.SmartRentalSystem.controller.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.rental.SmartRentalSystem.model.enums.BookingPaymentStatus;
import lk.rental.SmartRentalSystem.model.enums.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Builder
public class ViewAllBooking {
    private LocalDate startDate;
    private LocalDate endDate;
    // Total days (calculated)
    private Integer totalDays;
    // Pricing
    private Double totalPrice;
    // Booking status
    @Enumerated(EnumType.STRING)
    private BookingStatus status; // PENDING, CONFIRMED
    // Payment status
    @Enumerated(EnumType.STRING)
    private BookingPaymentStatus bookingPaymentStatus;
    // Timestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
