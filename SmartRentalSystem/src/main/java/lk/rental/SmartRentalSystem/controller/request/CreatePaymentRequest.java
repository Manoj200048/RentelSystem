package lk.rental.SmartRentalSystem.controller.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.rental.SmartRentalSystem.model.enums.PaymentMethod;
import lombok.Data;

@Data
public class CreatePaymentRequest {
    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}
