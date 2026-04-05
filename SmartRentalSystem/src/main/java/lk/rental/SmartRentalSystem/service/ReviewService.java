package lk.rental.SmartRentalSystem.service;

import lk.rental.SmartRentalSystem.controller.request.CreateReviewRequest;
import lk.rental.SmartRentalSystem.exception.ItemNotFoundException;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;

public interface ReviewService {
    void add(Long id, Long uid, CreateReviewRequest createReviewRequest)throws ItemNotFoundException, UserNotFoundException;
    Double findAverageRating(Long id) throws ItemNotFoundException;
}
