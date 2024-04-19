package com.springtech.jobarena.review;

import com.springtech.jobarena.company.Company;

import java.util.List;

public interface ReviewService {

    public List<Review> getAllReviews(Long companyId);

    public Boolean addReview(Long companyId, Review review);

    public Review getReviewById(Long companyId, Long reviewId);

    public Boolean updateReview(Long companyId, Long reviewId, Review updatedReview);

    public Boolean deleteReviewById(Long companyId, Long reviewId);
}
