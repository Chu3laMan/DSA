package com.my.SampleStore.service;

import java.util.List;
import java.util.Map;

import com.my.SampleStore.domain.Product;

public interface ProductService {
	
	public void updateAllStock();
	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	Product getProductById(String productID);
	void addProduct(Product p);
}
