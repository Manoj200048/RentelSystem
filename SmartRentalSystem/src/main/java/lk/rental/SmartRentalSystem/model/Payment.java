package lk.rental.SmartRentalSystem.model;

import jakarta.persistence.*;
import lk.rental.SmartRentalSystem.model.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private PaymentStatus paymentStatus ; // PENDING, SUCCESS, FAILED

    private String paymentMethod; // CARD, PAYPAL

    private String transactionId; // from gateway

    private LocalDateTime createdAt;

    // Relationship
    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
