package com.solt.triplenine.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.solt.triplenine.entity.StoreList;
import com.solt.triplenine.util.DatabaseManager;

public class StoreListService {
	public void add(StoreList stlist) {
		String sql = "insert into storelist (stlistdate,stlistname,stlistaddress,stlistnumber,stlistphno)values(?,?,?,?,?);";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setTimestamp(1, Timestamp.valueOf(stlist.getStlistdate()));
				stmt.setString(2, stlist.getStlistname());
				stmt.setString(3, stlist.getStlistaddress());
				stmt.setString(4, stlist.getStlistnumber());
				stmt.setString(5, stlist.getStlistphno());
				stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<StoreList>findAll() {
		// TODO Auto-generated method stub
		List<StoreList>list = new ArrayList<StoreList>();
		String sql = "select * from storelist";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StoreList stlist = new StoreList();
				stlist.setStlistdate(rs.getTimestamp("stlistdate").toLocalDateTime());
				stlist.setStlistname(rs.getString("stlistname"));
				stlist.setStlistaddress(rs.getString("stlistaddress"));
				stlist.setStlistnumber(rs.getString("stlistnumber"));
				stlist.setStlistphno(rs.getString("stlistphno"));
				list.add(stlist);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public List<StoreList> find(String text) {
		StringBuffer sb = new StringBuffer("Select * from storelist l ");
		List<StoreList>list = new ArrayList<StoreList>();
		List<Object>param = new ArrayList<Object>();
		
		if (!text.isEmpty()||text!=null) {
			sb.append("where l.stlistname like ? ");
			param.add(text.concat("%"));
		}
				
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(new String(sb))) {
			for (int i = 0; i < param.size(); i++) {
				stmt.setObject(i+1, param.get(i));
				
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StoreList stlist = new StoreList();
				stlist.setStlistdate(rs.getTimestamp("stlistdate").toLocalDateTime());
				stlist.setStlistname(rs.getString("stlistname"));
				stlist.setStlistaddress(rs.getString("stlistaddress"));
				stlist.setStlistnumber(rs.getString("stlistnumber"));
				stlist.setStlistphno(rs.getString("stlistphno"));
				list.add(stlist);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		// TODO Auto-generated method stub
		
	}
	public void delete(StoreList stlist) {
		String sql = "delete from storelist where stlistphno=?";
		try(Connection con = DatabaseManager.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, stlist.getStlistphno());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void update(StoreList stlist) {
		// TODO Auto-generated method stub
		String sql = "update storelist set stlistdate=?,stlistname=?,stlistaddress=?,stlistnumber=? where stlistphno=?";
		try(Connection con = DatabaseManager.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setTimestamp(1, Timestamp.valueOf(stlist.getStlistdate()));
			stmt.setString(2, stlist.getStlistname());
			stmt.setString(3, stlist.getStlistaddress());
			stmt.setString(4, stlist.getStlistnumber());
			stmt.setString(5, stlist.getStlistphno());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
