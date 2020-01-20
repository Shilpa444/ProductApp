package com.rakuten.training.spring.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rakuten.training.spring.domain.Product;
import com.rakuten.training.spring.service.ProductService;
@Component//("uiObj")
public class ProductConsoleUI {
  ProductService service;
@Autowired
  public void setService(ProductService service) {
    this.service = service;
  }

  public void ProductWithUI() {
    Scanner s = new Scanner(System.in);
    System.out.println("enter name");
    String name = s.nextLine();
    System.out.println("enter price");
    float price = Float.parseFloat(s.nextLine());
    System.out.println("enter qoh");
    int qoh = Integer.parseInt(s.nextLine());
    Product p = new Product(name, price, qoh);
    int id = service.addNewProduct(p);
    System.out.println("created product with id" + id);
    
  }
}
//create TABLE PUBLISHER(ID INT(6) PRIMARY KEY,NAME VARCHAR(10) NOT NULL,GENRE VARCHAR(10),NUMPAGES VARCHAR(10),PRICE INT(10));