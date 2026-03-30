package lk.rental.SmartRentalSystem.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ViewAllItemResponse {
    private List<ViewAllItem> viewAllItemList;
}
