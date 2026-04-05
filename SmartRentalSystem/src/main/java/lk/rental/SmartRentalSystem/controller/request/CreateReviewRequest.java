package lk.rental.SmartRentalSystem.controller.request;

import lombok.Data;

@Data
public class CreateReviewRequest {
    private Integer rating; // 1-5

    private String comment;
}
