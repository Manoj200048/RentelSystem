package lk.rental.SmartRentalSystem.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViewAverageRatingResponse {
    private Double averageRating;

}
