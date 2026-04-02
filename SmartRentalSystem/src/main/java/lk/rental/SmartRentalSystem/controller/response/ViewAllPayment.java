package lk.rental.SmartRentalSystem.controller.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.rental.SmartRentalSystem.model.enums.PaymentMethod;
import lk.rental.SmartRentalSystem.model.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ViewAllPayment {
    private Double amount;
    @Enumerated(EnumType.STRING)

    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)

    private PaymentStatus paymentStatus ;
    private String transactionId; // from gateway
    private LocalDateTime createdAt;
}
