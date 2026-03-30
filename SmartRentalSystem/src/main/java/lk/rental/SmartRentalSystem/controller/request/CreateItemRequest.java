package lk.rental.SmartRentalSystem.controller.request;

import lombok.Data;

@Data
public class CreateItemRequest {
    private String itemName;
    private Double pricePerDay;
    private String location;
    private String imageUrl;
}
