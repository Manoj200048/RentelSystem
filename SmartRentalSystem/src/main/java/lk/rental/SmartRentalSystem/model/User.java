package lk.rental.SmartRentalSystem.model;

import jakarta.persistence.*;
import lk.rental.SmartRentalSystem.model.enums.UserRole;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String mobileNumber;
    private String homeAddress;
    private String emailAddress;
    private String nicNumber;
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    private UserRole userRole; // Owner,Customer,Admin

    @OneToMany(mappedBy = "owner",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<OwnerItem> ownerItemList;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Review> reviewList;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Payment> paymentList;
}
