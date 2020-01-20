package com.rakuten.training.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.spring.dal.ProductDAO;
import com.rakuten.training.spring.dal.ReviewDAO;
import com.rakuten.training.spring.domain.Product;
import com.rakuten.training.spring.domain.Review;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDAO reviewDAO;

	@Autowired
	ProductDAO productDAO;

	@Override
	public int addReviewToProduct(int productId, Review toBeAdded) {

		Product product = productDAO.findById(productId);
		if (product == null) {
			throw new NoSuchProductException();
		}
		toBeAdded.setProduct(product);
		Review added = reviewDAO.save(toBeAdded);
		return added.getId();
	}

	@Override
	public List<Review> getByProductId(int productId) {

		return reviewDAO.findByProductId(productId);
	}

}
