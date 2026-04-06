package lk.rental.SmartRentalSystem.repository;

import lk.rental.SmartRentalSystem.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Page<Payment>findAll(Pageable pageable);
}
