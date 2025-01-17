package com.Hritik.JobApplication.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReviews(@PathVariable long companyId, @RequestBody Review review){
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if(isReviewSaved){
            return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable long companyId, @PathVariable long reviewId){
        Review review = reviewService.getReviewById(companyId, reviewId);
        if(review != null){
            return new ResponseEntity<>(reviewService.getReviewById(companyId,reviewId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable long companyId, @PathVariable long reviewId,
                                               @RequestBody Review updatedReview){
        boolean idUpdated = reviewService.updateReview(companyId, reviewId, updatedReview);
        if(idUpdated){
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not found with given id",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable long companyId, @PathVariable long reviewId){
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isDeleted){
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not Found", HttpStatus.NOT_FOUND);
    }
}
