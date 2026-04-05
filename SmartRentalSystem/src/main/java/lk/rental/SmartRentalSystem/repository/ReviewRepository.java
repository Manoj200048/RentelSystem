package lk.rental.SmartRentalSystem.repository;

import lk.rental.SmartRentalSystem.model.Item;
import lk.rental.SmartRentalSystem.model.Review;
import lk.rental.SmartRentalSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Boolean existsByCustomerAndItem(User user, Item item);
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.item.id = :itemId")
    Double getAverageRating(Long itemId);
}
