package com.my.SampleStore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Pattern(regexp = "")
public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Pattern(regexp="P[1-9]+", message="{Pattern.Product.productId.validation}")
	private String productId;
	@Size(min=4, max=50, message="{Size.Product.name.validation}")
	private String name;
	@Min(value=0, message="{Min.Product.unitPrice.validation}")
	@Digits(integer=8, fraction=2, message="{Digits.Product.unitPrice.validation}")
	@NotNull(message="{NotNull.Product.unitPrice.validation}")
	private BigDecimal unitPrice;
	
	private String description;
	private String manufacturer;
	private String category;
	private long unitsInStock;
	private long unitsInOrder;
	private boolean discontinued;
	private String condition;
	
	public Product() {}
	
	
	public Product(String productId, String name, BigDecimal unitPrice) {
		super();
		this.productId = productId;
		this.name = name;
		this.unitPrice = unitPrice;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public BigDecimal getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public long getUnitsInStock() {
		return unitsInStock;
	}


	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}


	public long getUnitsInOrder() {
		return unitsInOrder;
	}


	public void setUnitsInOrder(long unitsInOrder) {
		this.unitsInOrder = unitsInOrder;
	}


	public boolean isDiscontinued() {
		return discontinued;
	}


	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}


	public String getCondition() {
		return condition;
	}


	public void setCondition(String conidition) {
		this.condition = conidition;
	}
	
	
	
	

}
