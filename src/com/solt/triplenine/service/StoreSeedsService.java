package com.solt.triplenine.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.solt.triplenine.entity.StoreSeeds;
import com.solt.triplenine.util.DatabaseManager;

public class StoreSeedsService {
	public void add(StoreSeeds stseeds) {
		String sql = "insert into storeseeds (stseedsdate,stseedsname,stseedscategory,stseedstotal,stseedsaddress,stseedsphno)values(?,?,?,?,?,?);";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
				stmt.setString(2, stseeds.getStseedsname());
				stmt.setString(3, stseeds.getStseedscategory());
				stmt.setString(4, stseeds.getStseedstotal());
				stmt.setString(5, stseeds.getStseedsaddress());
				stmt.setString(6, stseeds.getStseedsphno());
				stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<StoreSeeds>findAll() {
		// TODO Auto-generated method stub
		List<StoreSeeds>list = new ArrayList<StoreSeeds>();
		String sql = "select * from storeseeds";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StoreSeeds stseeds = new StoreSeeds();
				stseeds.setStseedsdate(rs.getTimestamp("stseedsdate").toLocalDateTime());
				stseeds.setStseedsname(rs.getString("stseedsname"));
				stseeds.setStseedscategory(rs.getString("stseedscategory"));
				stseeds.setStseedstotal(rs.getString("stseedstotal"));
				stseeds.setStseedsaddress(rs.getString("stseedsaddress"));
				stseeds.setStseedsphno(rs.getString("stseedsphno"));
				list.add(stseeds);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public List<StoreSeeds>find(String text) {
		StringBuffer sb = new StringBuffer("Select * from storeseeds s ");
		List<StoreSeeds>list = new ArrayList<StoreSeeds>();
		List<Object>param = new ArrayList<Object>();
		
		if (!text.isEmpty()||text!=null) {
			sb.append("where s.stseedsname like ? ");
			param.add(text.concat("%"));
		}
				
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(new String(sb))) {
			for (int i = 0; i < param.size(); i++) {
				stmt.setObject(i+1, param.get(i));
				
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StoreSeeds stseeds = new StoreSeeds();
				stseeds.setStseedsdate(rs.getTimestamp("stseedsdate").toLocalDateTime());
				stseeds.setStseedsname(rs.getString("stseedsname"));
				stseeds.setStseedscategory(rs.getString("stseedscategory"));
				stseeds.setStseedstotal(rs.getString("stseedstotal"));
				stseeds.setStseedsaddress(rs.getString("stseedsaddress"));
				stseeds.setStseedsphno(rs.getString("stseedsphno"));
				list.add(stseeds);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		// TODO Auto-generated method stub
		
	}
	public void delete(StoreSeeds stseeds) {
		String sql = "delete from storeseeds where stseedsphno=?";
		try(Connection con = DatabaseManager.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, stseeds.getStseedsphno());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void update(StoreSeeds stseeds) {
		// TODO Auto-generated method stub
		String sql = "update storeseeds set stseedsdate=?,stseedsname=?,stseedscategory=?,stseedstotal=?,stseedsaddress=? where stseedsphno=?";
		try(Connection con = DatabaseManager.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setTimestamp(1, Timestamp.valueOf(stseeds.getStseedsdate()));
			stmt.setString(2, stseeds.getStseedsname());
			stmt.setString(3, stseeds.getStseedscategory());
			stmt.setString(4, stseeds.getStseedstotal());
			stmt.setString(5, stseeds.getStseedsaddress());
			stmt.setString(6, stseeds.getStseedsphno());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
