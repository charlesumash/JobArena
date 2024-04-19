package com.springtech.jobarena.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> findAllByCompanyId(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId),
                HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,
                                            @RequestBody Review review){
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if (isReviewSaved){
            return new ResponseEntity<>("Review successfully added", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,
                                                @PathVariable Long reviewId){
        Review review = reviewService.getReviewById(companyId, reviewId);
        if (review != null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long companyId,
                                                @PathVariable Long reviewId,
                                                   @RequestBody Review updatedReview){
        Boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, updatedReview);
        if (isReviewUpdated){
            return new ResponseEntity<>("Review successfully updated", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId,
                                                   @PathVariable Long reviewId){
        Boolean isReviewDeleted = reviewService.deleteReviewById(companyId, reviewId);
        if (isReviewDeleted){
            return new ResponseEntity<>("Review successfully deleted", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }
}
