package lk.rental.SmartRentalSystem.controller.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.rental.SmartRentalSystem.model.enums.AvailabilityStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViewAllItem {

    private String itemName;
    private Double pricePerDay;
    private String location;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus itemAvailability;
}
