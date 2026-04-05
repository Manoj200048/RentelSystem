package lk.rental.SmartRentalSystem.service.impl;

import lk.rental.SmartRentalSystem.controller.request.CreateReviewRequest;
import lk.rental.SmartRentalSystem.exception.ItemNotFoundException;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;
import lk.rental.SmartRentalSystem.model.Item;
import lk.rental.SmartRentalSystem.model.Review;
import lk.rental.SmartRentalSystem.model.User;
import lk.rental.SmartRentalSystem.repository.BookingRepository;
import lk.rental.SmartRentalSystem.repository.ItemRepository;
import lk.rental.SmartRentalSystem.repository.ReviewRepository;
import lk.rental.SmartRentalSystem.repository.UserRepository;
import lk.rental.SmartRentalSystem.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Override
    public void add(Long id, Long uid, CreateReviewRequest createReviewRequest) throws ItemNotFoundException, UserNotFoundException {
        User user = userRepository.findById(uid).orElseThrow(
                ()->new UserNotFoundException("User Not Found..!")
        );

        Item item = itemRepository.findById(id).orElseThrow(
                ()->new ItemNotFoundException("Item Not Found..!")
        );

        //check booking exist
        Boolean hasBooked = bookingRepository.existsByCustomerAndItem(user, item);
        if (!hasBooked) {
            throw new RuntimeException("You must book before reviewing");
        }

        //check duplicate review
        if(reviewRepository.existsByCustomerAndItem(user,item)){
            throw new RuntimeException("You already reviewed this item..!");
        }

        Review review = new Review();

        review.setItem(item);
        review.setCustomer(user);
        review.setRating(createReviewRequest.getRating());
        review.setComment(createReviewRequest.getComment());

        reviewRepository.save(review);

    }

    public Double findAverageRating(Long id) throws ItemNotFoundException{
        Item item = itemRepository.findById(id).orElseThrow(
                ()->new ItemNotFoundException("Item Not Found..!")
        );

        Double avg = reviewRepository.getAverageRating(item.getId());

        if(avg == null){
            return 0.0;
        }

        return avg;

    }
}
