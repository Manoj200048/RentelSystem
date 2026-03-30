package lk.rental.SmartRentalSystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OwnerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
