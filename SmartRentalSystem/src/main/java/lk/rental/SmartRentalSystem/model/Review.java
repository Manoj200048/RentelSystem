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

    private Integer rating;

    private String comment;

    // User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Item
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
