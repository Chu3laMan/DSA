package com.my.SampleStore.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.my.SampleStore.domain.Product;

public class ProductRepositoryImpl implements ProductRepository {
	
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParamaterJdbcTemplate;
	
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		namedParamaterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}
  
	@Override
	public List<Product> getAllProducts() {
		String query = "SELECT * FROM PRODUCTS";
		List<Product> result = namedParamaterJdbcTemplate.query(query, new ProductMapper());
		return result;
	}
	
	
	private static final class ProductMapper implements RowMapper<Product> {

		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setProductId(rs.getString("ID"));
			product.setName(rs.getString("NAME"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
			product.setManufacturer(rs.getString("MANUFACTURER"));
			product.setCategory(rs.getString("CATEGORY"));
			product.setCondition(rs.getString("cond"));
			product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
			product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
			product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
			return product;
		}
		
	}

   
	@Override
	public void updateStock(String productId, long noOfUnits) {
		String SQL = "UPDATE PRODUCTS SET UNITS_IN_STOCK = :noOfUnits WHERE ID = :productId";
		Map<String, Object> params = new HashMap<>();
		params.put("unitsInStock", noOfUnits);
		namedParamaterJdbcTemplate.query(SQL, params, new ProductMapper());
	}
	
	

	@Override
	public List<Product> getProductByCategory(String category) {
		String query = "SELECT * FROM PRODUCTS WHERE CATEGORY = :category";
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		List<Product> result = namedParamaterJdbcTemplate.query(query, params, new ProductMapper());
		return result;
	}

	@Override
	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		String query = "SELECT * FROM PRODUCTS WHERE CATEGORY IN (:categories) AND MANUFACTURER IN (:brands)";
		return namedParamaterJdbcTemplate.query(query, filterParams,new ProductMapper());
	}

	@Override
	public Product getProductById(String productID) {
		String query = "SELECT * FROM PRODUCTS WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", productID);
		return namedParamaterJdbcTemplate.queryForObject(query, params, new ProductMapper());
	}

	@Override
	public void addProduct(Product p) {
		String query = "INSERT INTO PRODUCTS (ID, "
				+ "NAME,"
				+ "DESCRIPTION,"
				+ "UNIT_PRICE,"
				+ "MANUFACTURER,"
				+ "CATEGORY,"
				+ "cond,"
				+ "UNITS_IN_STOCK,"
				+ "UNITS_IN_ORDER,"
				+ "DISCONTINUED) "
				+ "VALUES(:id, :name, :desc, :price, :manufacturer, :category, :condition, :inStock, :inOrder, :discontinued)";
		Map<String, Object> params = new HashMap<>();
		params.put("id", p.getProductId());
		params.put("name", p.getName());
		params.put("desc", p.getDescription());
		params.put("price", p.getUnitPrice());
		params.put("manufacturer", p.getManufacturer());
		params.put("category", p.getCategory());
		params.put("condition", p.getCondition());
		params.put("inStock", p.getUnitsInStock());
		params.put("inOrder", p.getUnitsInOrder());
		params.put("discontinued", p.isDiscontinued());
		namedParamaterJdbcTemplate.update(query, params);
	}

	
}
