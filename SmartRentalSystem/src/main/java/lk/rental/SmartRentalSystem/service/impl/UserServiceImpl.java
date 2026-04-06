package lk.rental.SmartRentalSystem.service.impl;

import lk.rental.SmartRentalSystem.controller.request.CreateUserRequest;
import lk.rental.SmartRentalSystem.controller.request.UpdateUserRequest;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;
import lk.rental.SmartRentalSystem.model.User;
import lk.rental.SmartRentalSystem.repository.UserRepository;
import lk.rental.SmartRentalSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public void add(CreateUserRequest createUserRequest) {
        User user = new User();

        user.setName(createUserRequest.getName());
        user.setMobileNumber(createUserRequest.getMobileNumber());
        user.setHomeAddress(createUserRequest.getHomeAddress());
        user.setEmailAddress(createUserRequest.getEmailAddress());
        user.setNicNumber(createUserRequest.getNicNumber());
        user.setPhotoUrl(createUserRequest.getPhotoUrl());
        user.setUserRole(createUserRequest.getUserRole());

        userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) throws UserNotFoundException {

        return userRepository.findById(id).orElseThrow(
                ()->new UserNotFoundException("User Not Found...!")
        );
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(Long id, UpdateUserRequest updateUserRequest) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(
                ()->new UserNotFoundException("User Not Found...!")
        );

        user.setName(updateUserRequest.getName());
        user.setMobileNumber(updateUserRequest.getMobileNumber());
        user.setHomeAddress(updateUserRequest.getHomeAddress());
        user.setEmailAddress(updateUserRequest.getEmailAddress());
        user.setNicNumber(updateUserRequest.getNicNumber());
        user.setPhotoUrl(updateUserRequest.getPhotoUrl());
        user.setUserRole(updateUserRequest.getUserRole());

        userRepository.save(user);
    }

    @Override
    public void delete(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(
                ()->new UserNotFoundException("User Not Found...!")
        );

        userRepository.delete(user);
    }

    @Override
    public Page<User> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }
}
