package lk.rental.SmartRentalSystem.repository;

import lk.rental.SmartRentalSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
