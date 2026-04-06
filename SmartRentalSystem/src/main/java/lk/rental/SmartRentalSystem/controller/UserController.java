package lk.rental.SmartRentalSystem.controller;

import lk.rental.SmartRentalSystem.controller.request.CreateUserRequest;
import lk.rental.SmartRentalSystem.controller.request.UpdateUserRequest;
import lk.rental.SmartRentalSystem.controller.response.PaginatedResponse;
import lk.rental.SmartRentalSystem.controller.response.ViewAllUser;
import lk.rental.SmartRentalSystem.controller.response.ViewUserResponse;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;
import lk.rental.SmartRentalSystem.model.User;
import lk.rental.SmartRentalSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add",headers = "X-Api-Version=v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody CreateUserRequest createUserRequest){
       userService.add(createUserRequest);
    }

    @GetMapping(value = "/{user-id}",headers ="X-Api-Version=v1" )
    public ViewUserResponse getUser(@PathVariable("user-id")Long id) throws UserNotFoundException {
          User user =userService.findUserById(id);

          return ViewUserResponse
                  .builder()
                  .name(user.getName())
                  .mobileNumber(user.getMobileNumber())
                  .homeAddress(user.getHomeAddress())
                  .emailAddress(user.getEmailAddress())
                  .nicNumber(user.getNicNumber())
                  .photoUrl(user.getPhotoUrl())
                  .userRole(user.getUserRole())
                  .build();
    }

    @GetMapping(headers = "X-Api-Version=v1")
    public PaginatedResponse<ViewAllUser> getAll(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size) {

        Page<User> userPage = userService.findAll(page, size);

        List<ViewAllUser> content = userPage.getContent().stream()
                .map(user -> ViewAllUser.builder()
                        .name(user.getName())
                        .mobileNumber(user.getMobileNumber())
                        .homeAddress(user.getHomeAddress())
                        .emailAddress(user.getEmailAddress())
                        .nicNumber(user.getNicNumber())
                        .photoUrl(user.getPhotoUrl())
                        .userRole(user.getUserRole())
                        .build())
                .collect(Collectors.toList());

        return PaginatedResponse.<ViewAllUser>builder()
                .content(content)
                .page(userPage.getNumber())
                .size(userPage.getSize())
                .totalElements(userPage.getTotalElements())
                .totalPages(userPage.getTotalPages())
                .last(userPage.isLast())
                .build();
    }

    @PutMapping(value = "/{user-id}",headers ="X-Api-Version=v1")
    public void updateUser(@PathVariable("user-id") Long id, @RequestBody UpdateUserRequest updateUserRequest)throws UserNotFoundException{
       userService.update(id,updateUserRequest);
    }

    @DeleteMapping(value = "/{user-id}",headers ="X-Api-Version=v1")
    public void delete(@PathVariable("user-id")Long id)throws UserNotFoundException{
        userService.delete(id);
    }
}
