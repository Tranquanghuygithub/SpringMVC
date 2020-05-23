package com.springmvc.DAO.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springmvc.DAO.UserDAO;
import com.springmvc.entity.UserEntity;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	

	@Override
	public void addUser(UserEntity user) {
		sessionFactory.getCurrentSession().save(user);
		
	}

	@Override
	public void updateUser(UserEntity user) {
		sessionFactory.getCurrentSession().merge(user);
		
	}

	@Override
	public void deleteUser(long id) {
		sessionFactory.getCurrentSession().delete(getUserById(id));
		
	}

	@Override
	public UserEntity getUserById(long id) {
		return  (UserEntity) sessionFactory.getCurrentSession().get(UserEntity.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> getAllUsers() {
		Criteria criteria =sessionFactory.getCurrentSession().createCriteria(UserEntity.class);
		return criteria.list();
		
	}

//Chuyen sang dung hibernate tuwj dong mapping  tu entity  vao csld va nguoc lai
	//@Autowired
	
//	JdbcTemplate jdbcTemplate; // tu dong gan bean jdbc template vao = bien nay de su dung
//	
//	@Override
//	public void addUser(UserEntity user) {
//		String sql = "INSERT INTO USER(NAME, PHONE) "
//				+ " VALUES(?,?)";
//	}
//
//	@Override
//	public void updateUser(UserEntity user) {
//		String sql = "UPDATE BANHANG.USER SET NAME=?, PHONE=?"
//				+ " WHERE id=?";
//		jdbcTemplate.update(sql, user.getName(), user.getPhone(),user.getId());		
//	}
//
//	@Override
//	public void deleteUser(long id) {
//		String sql= "DELETE FROM USER WHERE ID=?";
//		jdbcTemplate.update(sql,id);
//		
//	}
//
//	@Override
//	public UserEntity getUserById(long id) {
//		 String sql= "SELECT * FROM USER"
//		 		    + " WHERE ID=? ";
//		 return jdbcTemplate.queryForObject(sql, new Object[] {id}, new RowMapper<UserEntity>() {
//
//			@Override
//			public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
//				UserEntity  user = new UserEntity();
//				user.setId(rs.getLong("id"));
//				user.setName(rs.getString("name"));
//				user.setPhone(rs.getString("phone"));
//				return user;
//				
//			}
//			 
//		 });
//		
//	}
//
//	@Override
//	public List<UserEntity> getAllUsers() {
//		String sql= "SELECT * FROM USER";
//		return jdbcTemplate.query(sql, new RowMapper<UserEntity>() {
//
//			@Override
//			public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {       
//				UserEntity user= new UserEntity();
//					user.setId(rs.getLong("id"));
//					user.setName(rs.getString("name"));
//					user.setPhone( rs.getString("phone"));
//				    return user;	
//				}
//			      
//			}
//		);
//		
//	}

}
