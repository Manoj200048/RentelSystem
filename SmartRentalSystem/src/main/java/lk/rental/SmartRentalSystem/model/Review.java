package lk.rental.SmartRentalSystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating; // 1-5

    private String comment;

    // User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User customer;

    // Item
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
