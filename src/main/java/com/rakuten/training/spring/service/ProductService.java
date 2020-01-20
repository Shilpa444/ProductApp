package com.rakuten.training.spring.service;

import java.util.List;

import com.rakuten.training.spring.domain.Product;

public interface ProductService {
  int addNewProduct(Product toBeAdded);

  void removeProduct(int id);

  List<Product> findAll();

  Product findById(int id);
}
