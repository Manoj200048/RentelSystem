package lk.rental.SmartRentalSystem.model;

import jakarta.persistence.*;
import lk.rental.SmartRentalSystem.model.enums.AvailabilityStatus;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private Double pricePerDay;
    private String location;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus itemAvailability;

    @OneToMany(mappedBy = "item",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<OwnerItem> ownerItemList;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "item",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "item",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Review> reviewList;

}
