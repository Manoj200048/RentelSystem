package lk.rental.SmartRentalSystem.repository;

import lk.rental.SmartRentalSystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
