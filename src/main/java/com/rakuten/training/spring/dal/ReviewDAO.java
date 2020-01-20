package com.rakuten.training.spring.dal;

import java.util.List;

import com.rakuten.training.spring.domain.Review;

public interface ReviewDAO {

  Review findById(int id);

  Review save(Review toBeSaved);

  void deleteById(int id);

  List<Review> findAll();
  
  List<Review> findByProductId(int productId);
}