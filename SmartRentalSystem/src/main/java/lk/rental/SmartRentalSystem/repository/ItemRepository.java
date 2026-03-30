package lk.rental.SmartRentalSystem.repository;

import lk.rental.SmartRentalSystem.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
