package lk.rental.SmartRentalSystem.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ViewAllCategoryListResponse {
    private List<ViewAllCategory> viewAllCategoryResponseList;

}
