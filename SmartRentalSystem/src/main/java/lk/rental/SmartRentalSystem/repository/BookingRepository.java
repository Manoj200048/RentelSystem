package lk.rental.SmartRentalSystem.repository;

import lk.rental.SmartRentalSystem.model.Booking;
import lk.rental.SmartRentalSystem.model.Item;
import lk.rental.SmartRentalSystem.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query("""
    SELECT b FROM Booking b
    WHERE b.item.id = :itemId
    AND b.status <> 'CANCELLED'
    AND (
        :startDate <= b.endDate AND
        :endDate >= b.startDate
    )
""")
    List<Booking> findConflictingBookings(
            Long itemId,
            LocalDate startDate,
            LocalDate endDate
    );


    Boolean existsByCustomerAndItem(User user, Item item);

    Page<Booking>findAll(Pageable pageable);
}
