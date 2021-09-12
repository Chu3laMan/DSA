package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DSA.BST;
import DSA.BSTImpl;
import domain.User;



public class UserDB {
	
	
	public static int insert(User user) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();  //get an object of the Connection Pool class
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "INSERT INTO userDetail (firstName, lastName, email, password) " 
		        + "VALUES (?, ?, ?, ?)";
		BSTImpl bst = null;
		try {
			bst = new BSTImpl();
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			return ps.executeUpdate();
		}catch(SQLException e) {
			System.err.println(e);
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		
	}
	
	public static List<User> selectUser() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		ArrayList<User> al = new ArrayList<User>();
		String query = "SELECT * FROM userDetail";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				al.add(user);
			}
		}catch(SQLException e) {
			System.err.println(e);
			return null;
		}
		return al;
	}
	
	public static List<User> findLastName(String firstName) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT firstName, lastName, email "
				+ "FROM userDetail "
				+ "WHERE firstName Like ?";
		try {
			ps = conection.prepareStatement(query);
			ps.setString(1, "%" + firstName + "%");
			rs = ps.executeQuery();
			ArrayList<User> al = new ArrayList<User>();
			while(rs.next()) {
				User u = new User();
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));
				u.setEmail(rs.getString("email"));
				al.add(u);
			}
			return al;
		}catch(SQLException e) {
			System.err.println(e);
			return null;
		}finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(conection);
		}
		
		
	}
	
	
	public static int updateUserPswd(String pswd) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "UPDATE userDetail "
				+ "SET userDetail.password = ? "
				+ "WHERE email = ?";
		
		try {
			User u = new User();
			ps = connection.prepareStatement(query);
			ps.setString(1, pswd);
			ps.setString(2, u.getEmail());
			return ps.executeUpdate();
		}catch(SQLException e) {
			System.err.println(e);
			return 0;
			
		}finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
	
	public static boolean checkEmail(String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT email FROM userDetail " 
				+ "WHERE email = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			return rs.next();
		}catch(SQLException e) {
			System.err.println(e);
			return false;
		}finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
	
	
	

}
