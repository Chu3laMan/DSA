package com.my.SampleStore.DAO;

import java.util.List;
import java.util.Map;

import com.my.SampleStore.domain.Product;

public interface ProductRepository {
	
	List<Product> getAllProducts();
	public void updateStock(String productId, long noOfUnits);
	List<Product> getProductByCategory(String category);
	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	Product getProductById(String productID);
	public void addProduct(Product p);
}
