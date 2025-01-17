package com.Hritik.JobApplication.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(long companyId);
    boolean addReview(long companyId, Review review);
    Review getReviewById(long companyId, long reviewId);
    boolean updateReview(long companyId, long reviewId, Review updatedReview);
    boolean deleteReview(long companyId, long reviewId);
}
