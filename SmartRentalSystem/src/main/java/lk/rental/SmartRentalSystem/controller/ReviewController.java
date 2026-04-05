package lk.rental.SmartRentalSystem.controller;

import lk.rental.SmartRentalSystem.controller.request.CreateReviewRequest;
import lk.rental.SmartRentalSystem.controller.response.ViewAverageRatingResponse;
import lk.rental.SmartRentalSystem.exception.ItemNotFoundException;
import lk.rental.SmartRentalSystem.exception.UserNotFoundException;
import lk.rental.SmartRentalSystem.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping(value = "/add/{item-id}/{user-id}",headers = "X-Api-Version=v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable("item-id")Long id, @PathVariable("user-id")Long uid, @RequestBody CreateReviewRequest createReviewRequest)throws ItemNotFoundException, UserNotFoundException{
        reviewService.add(id,uid,createReviewRequest);
    }

    @GetMapping(value = "/averageRating/{item-id}",headers = "X-Api-Version=v1")
    public ViewAverageRatingResponse getAverageRating(@PathVariable("item-id")Long id) throws ItemNotFoundException{
        Double avg = reviewService.findAverageRating(id);

        return ViewAverageRatingResponse
                .builder()
                .averageRating(avg)
                .build();

    }

}
