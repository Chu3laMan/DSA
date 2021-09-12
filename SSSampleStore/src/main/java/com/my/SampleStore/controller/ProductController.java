package com.my.SampleStore.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.SampleStore.DAO.ProductRepository;
import com.my.SampleStore.DAO.ProductRepositoryImpl;
import com.my.SampleStore.domain.Product;
import com.my.SampleStore.service.ProductService;



@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	//list() method shows all products available on the stock
	@RequestMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
		
	}
	
	
	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		productService.updateAllStock();
		return "redirect:/products";
	}
	
	//list() method uses PathVariable annotation to show up the category of each product, in other words, list() method arranges each products on its category
	@RequestMapping("/products/{category}")
	public String list(Model model, @PathVariable("category") String productCategory) {
		model.addAttribute("product", productRepository.getProductByCategory(productCategory));
		return "products";
		
	}
	
	
	//getProductsByFilter() method uses matrix variables to bind variable on the URL
	@RequestMapping("/products/filter/{params}")
	public String getProductsByFilter(@MatrixVariable (pathVar="params") Map<String, List<String>> filterParams, Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}
	
	
	//below methods are used to add new product to the store, moreover, it used form backing bean in order to get information
	//from the HTML form elements in order to process them in the Controller
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}
	
	@RequestMapping(value="/products/add", method = RequestMethod.POST)
	public String procesAndNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result) {
		productService.addProduct(newProduct);
		String[] supressedFields = result.getSuppressedFields();
		if(supressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(supressedFields));
		}
		return "redirect:/market/products";
	}
	
	
	//intializeBinder() method set up the fields which allowed for binding during submission
	@InitBinder("newProduct")
	public void intializeBinder(WebDataBinder binder) {
		binder.setAllowedFields("productId",
				"name",
				"unitPrice",
				"description",
				"manufacturer",
				"category",
				"unitsInStock",
				"unitsInOrder",
				"discontinued",
				"condition");
	}
	
	
	
	
	

}
