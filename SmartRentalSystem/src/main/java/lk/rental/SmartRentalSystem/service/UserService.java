package lk.rental.SmartRentalSystem.service;

import lk.rental.SmartRentalSystem.controller.request.CreateUserRequest;
import lk.rental.SmartRentalSystem.controller.request.UpdateUserRequest;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;
import lk.rental.SmartRentalSystem.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void add(CreateUserRequest createUserRequest);
    User findUserById(Long id) throws UserNotFoundException;
    List<User> findAll();
    void update(Long id, UpdateUserRequest updateUserRequest) throws UserNotFoundException;
    void delete(Long id)throws UserNotFoundException;
    Page<User> findAll(int page, int size);

}
