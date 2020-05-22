package com.springmvc.DAO.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springmvc.DAO.UserDAO;
import com.springmvc.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	JdbcTemplate jdbcTemplate; // tu dong gan bean jdbc template vao = bien nay de su dung
	
	@Override
	public void addUser(User user) {
		String sql= "INSERT INTO USER(NAME, PHONE) "
				+ " VALUES(?,?)";
	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE BANHANG.USER SET NAME=?, PHONE=?"
				+ " WHERE id=?";
		jdbcTemplate.update(sql, user.getName(), user.getPhone(),user.getId());		
	}

	@Override
	public void deleteUser(long id) {
		String sql= "DELETE FROM USER WHERE ID=?";
		jdbcTemplate.update(sql,id);
		
	}

	@Override
	public User getUserById(long id) {
		 String sql= "SELECT * FROM USER"
		 		    + " WHERE ID=? ";
		 return jdbcTemplate.queryForObject(sql, new Object[] {id}, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User  user = new User();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				return user;
				
			}
			 
		 });
		
	}

	@Override
	public List<User> getAllUsers() {
		String sql= "SELECT * FROM USER";
		return jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {       
					User user= new User();
					user.setId(rs.getLong("id"));
					user.setName(rs.getString("name"));
					user.setPhone( rs.getString("phone"));
				    return user;	
				}
			      
			}
		);
		
	}

}
