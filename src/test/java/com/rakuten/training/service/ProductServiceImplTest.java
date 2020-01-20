package com.rakuten.training.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.rakuten.training.spring.dal.ProductDAO;
import com.rakuten.training.spring.domain.Product;
import com.rakuten.training.spring.service.ProductServiceImpl;

import junit.framework.Assert;

public class ProductServiceImplTest {

	@Test
	public void addNewProduct_Returns_Valid_Id_When_ProductValue_GTEQ_MinValue() {

		// Arrange
		ProductServiceImpl service = new ProductServiceImpl();
		Product tobeAdded = new Product("test", 10000, 1); // notice 10000x1 >=10000
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Product saved = new Product("test", 10000, 1);
		saved.setId(1);
		Mockito.when(mockDAO.save(tobeAdded)).thenReturn(saved);
		service.setDao(mockDAO);
		// Act
		int id = service.addNewProduct(tobeAdded);

		// Assert

		assertTrue(id > 0);

	}

	@Test(expected = IllegalArgumentException.class)
	public void addNewProduct_Throws_When_ProductValues_LT_MinValue() {
		// Arrange
		ProductServiceImpl service = new ProductServiceImpl();
		Product tobeAdded = new Product("test", 9999, 1); // notice 9999 x 1 >=10000
		// Act
		service.addNewProduct(tobeAdded);
		// Assert

	}

	@Test
	public void testRemoveProduct_remove_if_MonetoryValue_GT_MinValue() {
		ProductServiceImpl service = new ProductServiceImpl();
		Product tobeAdded = new Product("test", 1000, 1);
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		tobeAdded.setId(3);
//		saved.setId(3);
		Mockito.when(mockDAO.findById(3)).thenReturn(tobeAdded);
		service.setDao(mockDAO);
		// Act
		service.removeProduct(3);
		// Assert
		Mockito.verify(mockDAO).deleteById(3);

	}

	@Test(expected = IllegalStateException.class)
	public void testRemoveProduct_remove_if_MonetoryValue_GT_MibValue() {
		// Arrange
		ProductServiceImpl service = new ProductServiceImpl();
		Product tobeAdded = new Product("test", 9999999, 9);
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		tobeAdded.setId(3);
		Mockito.when(mockDAO.findById(3)).thenReturn(tobeAdded);
		service.setDao(mockDAO);
		// Act
		service.removeProduct(3);
		// Assert
	}

}
