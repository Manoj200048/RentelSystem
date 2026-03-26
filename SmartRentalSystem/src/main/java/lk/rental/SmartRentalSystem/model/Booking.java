package lk.rental.SmartRentalSystem.model;

import jakarta.persistence.*;
import lk.rental.SmartRentalSystem.model.enums.BookingStatus;
import lk.rental.SmartRentalSystem.model.enums.BookingPaymentStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "bookings")
public class Booking {
    @Id
    private Long id;

    // Booking period
    private LocalDate startDate;
    private LocalDate endDate;

    // Total days (calculated)
    private Integer totalDays;

    // Pricing
    private Double totalPrice;

    // Booking status
    @Enumerated(EnumType.STRING)
    private BookingStatus status; // PENDING, CONFIRMED, CANCELLED, COMPLETED

    // Payment status
    @Enumerated(EnumType.STRING)
    private BookingPaymentStatus paymentStatus; // PAID, UNPAID

    // Timestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Payment payment;


}
