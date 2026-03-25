package lk.rental.SmartRentalSystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "items")
public class Item {
    @Id
    private Long id;

    private Double pricePerDay;
    private boolean available;
    private String location;
    private String imageUrl;

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
