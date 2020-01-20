package com.rakuten.training.spring.dal;
import java.util.*;

import com.rakuten.training.spring.domain.Product;

public interface ProductDAO {
	Product save(Product toBeSaved);
	Product findById(int id);
	List<Product> findAll();
	void deleteById(int id);
	}