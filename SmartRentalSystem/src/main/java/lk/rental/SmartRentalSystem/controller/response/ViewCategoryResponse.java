package lk.rental.SmartRentalSystem.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViewCategoryResponse {
    private String name;
    private String description;
}
