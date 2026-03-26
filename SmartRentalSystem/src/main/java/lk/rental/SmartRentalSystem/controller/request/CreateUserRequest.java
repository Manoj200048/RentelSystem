package lk.rental.SmartRentalSystem.controller.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.rental.SmartRentalSystem.model.enums.UserRole;
import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String mobileNumber;
    private String homeAddress;
    private String emailAddress;
    private String nicNumber;
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
