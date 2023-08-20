package com.solt.triplenine.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.solt.triplenine.entity.Category;
import com.solt.triplenine.util.DatabaseManager;

public class CategoryService {
	
	public void add(Category category) {
		String sql = "insert into category (cdate,cname,coneperprice)values(?,?,?);";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setTimestamp(1, Timestamp.valueOf(category.getCdate()));
				stmt.setString(2, category.getCname());
				stmt.setString(3, category.getConeperprice());
				stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<Category>findAll() {
		// TODO Auto-generated method stub
		List<Category>list = new ArrayList<Category>();
		String sql = "select * from category";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCdate(rs.getTimestamp("cdate").toLocalDateTime());
				category.setCname(rs.getString("cname"));
				category.setConeperprice(rs.getString("coneperprice"));
				list.add(category);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public List<Category> find(String text) {
		StringBuffer sb = new StringBuffer("Select * from category c ");
		List<Category>list = new ArrayList<Category>();
		List<Object>param = new ArrayList<Object>();
		
		if (!text.isEmpty()||text!=null) {
			sb.append("where c.cname like ? ");
			param.add(text.concat("%"));
		}
				
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(new String(sb))) {
			for (int i = 0; i < param.size(); i++) {
				stmt.setObject(i+1, param.get(i));
				
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCdate(rs.getTimestamp("cdate").toLocalDateTime());
				category.setCname(rs.getString("cname"));
				category.setConeperprice(rs.getString("coneperprice"));
				list.add(category);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		// TODO Auto-generated method stub
		
	}
	public void delete(Category category) {
		String sql = "delete from category where cname=?";
		try(Connection con = DatabaseManager.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, category.getCname());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void update(Category category) {
		// TODO Auto-generated method stub
		String sql = "update category set coneperprice=?,cdate=? where cname=?";
		try(Connection con = DatabaseManager.getConnection();
				
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, category.getConeperprice());
			stmt.setTimestamp(2, Timestamp.valueOf(category.getCdate()));
			stmt.setString(3, category.getCname());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
