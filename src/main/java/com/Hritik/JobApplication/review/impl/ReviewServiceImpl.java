package com.Hritik.JobApplication.review.impl;

import com.Hritik.JobApplication.company.Company;
import com.Hritik.JobApplication.company.CompanyService;
import com.Hritik.JobApplication.review.Review;
import com.Hritik.JobApplication.review.ReviewRepository;
import com.Hritik.JobApplication.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(long companyId, long reviewId) {
        return reviewRepository.findByCompanyIdAndId(companyId, reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(long companyId, long reviewId, Review updatedReview) {
        Review review = reviewRepository.findByCompanyIdAndId(companyId,reviewId).orElse(null);
        if(review != null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRatings(updatedReview.getRatings());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(long companyId, long reviewId) {
        Review review = reviewRepository.findByCompanyIdAndId(companyId, reviewId).orElse(null);
        if(review != null){
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

}
