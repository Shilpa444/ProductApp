package com.rakuten.training.spring.service;

import java.util.List;

import com.rakuten.training.spring.domain.Review;

public interface ReviewService {
	
	public int addReviewToProduct(int productId,Review toBeAdded);
	public List<Review> getByProductId(int productId);

}
