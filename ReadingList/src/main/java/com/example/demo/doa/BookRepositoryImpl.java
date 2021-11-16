package com.example.demo.doa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {
	
	private static final class BookMapper implements RowMapper<Book> {

		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book book = new Book();
			book.setId(rs.getLong("id"));
			book.setReader(rs.getString("reader"));
			book.setIsbn(rs.getString("isbn"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setDescription(rs.getString("description"));
			return book;
		}
		
	}
	
	@Autowired
	private NamedParameterJdbcTemplate namedParamaterJdbcTemplate;
	
	
	

	@Override
	public List<Book> findAll() {
		String query = "SELECT * FROM book";
		List<Book> result = namedParamaterJdbcTemplate.query(query, new BookMapper());
		return result;
	}

	@Override
	public List<Book> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Book> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Book> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Book> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<Book> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Book> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Book> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Book> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Book> S save(S entity) {
		return null;
	}

	@Override
	public Optional<Book> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Book entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Book> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Book> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Book> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Book> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> findByReader(String reader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBook(Book book) {
		String query = "INSERT INTO book (author, "
				+ "description,"
				+ "isbn,"
				+ "reader,"
				+ "title) "
				+ "VALUES(:author, :description, :isbn, :reader, :title)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("author", book.getAuthor());
		params.put("description", book.getDescription());
		params.put("isbn", book.getIsbn());
		params.put("reader", book.getReader());
		params.put("title", book.getTitle());
		namedParamaterJdbcTemplate.update(query, params);
	}

	
	@Override
	public Book getByReader(String reader) {
		String query = "SELECT * FROM book WHERE reader = :reader";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("reader", reader);
		return namedParamaterJdbcTemplate.queryForObject(query, params, new BookMapper());
	}

}
