package com.springtech.jobarena.review.Impl;

import com.springtech.jobarena.company.Company;
import com.springtech.jobarena.company.CompanyRepository;
import com.springtech.jobarena.company.CompanyService;
import com.springtech.jobarena.review.Review;
import com.springtech.jobarena.review.ReviewRepository;
import com.springtech.jobarena.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CompanyService companyService;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findAllByCompanyId(companyId);
    }

    @Override
    public Boolean addReview(Long companyId, Review review) {
        if (companyService.getCompanyById(companyId) != null){
            review.setCompany(companyService.getCompanyById(companyId));
            reviewRepository.save(review);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getReviewId().equals(reviewId))
                .findFirst().orElse(null);
    }

    @Override
    public Boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if (companyService.getCompanyById(companyId) != null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setReviewId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteReviewById(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId) != null
        && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.getReferenceById(reviewId);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
