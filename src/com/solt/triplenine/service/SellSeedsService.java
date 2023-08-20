package com.solt.triplenine.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.solt.triplenine.entity.SellSeeds;
import com.solt.triplenine.util.DatabaseManager;

public class SellSeedsService {
	public void add(SellSeeds sellseeds) {
		String sql = "insert into sellseeds (sellseedsdate,sellseedsname,sellseedscategory,sellseedstotal,sellseedsoneperprice,sellseedsaddress,sellseedstotalprice)values(?,?,?,?,?,?,?);";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
				stmt.setString(2, sellseeds.getSellseedsname());
				stmt.setString(3, sellseeds.getSellseedscategory());
				stmt.setString(4, sellseeds.getSellseedstotal());
				stmt.setString(5, sellseeds.getSellseedsoneperprice());
				stmt.setString(6, sellseeds.getSellseedsaddress());
				stmt.setString(7, sellseeds.getSellseedstotalprice());
				stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<SellSeeds>findAll() {
		// TODO Auto-generated method stub
		List<SellSeeds>list = new ArrayList<SellSeeds>();
		String sql = "select * from sellseeds";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SellSeeds sellseeds = new SellSeeds();
				sellseeds.setSellseedsdate(rs.getTimestamp("sellseedsdate").toLocalDateTime());
				sellseeds.setSellseedsname(rs.getString("sellseedsname"));
				sellseeds.setSellseedscategory(rs.getString("sellseedscategory"));
				sellseeds.setSellseedstotal(rs.getString("sellseedstotal"));
				sellseeds.setSellseedsoneperprice(rs.getString("sellseedsoneperprice"));
				sellseeds.setSellseedsaddress(rs.getString("sellseedsaddress"));
				sellseeds.setSellseedstotalprice(rs.getString("sellseedstotalprice"));
				list.add(sellseeds);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public List<SellSeeds>find(String text) {
		StringBuffer sb = new StringBuffer("Select * from sellseeds d ");
		List<SellSeeds>list = new ArrayList<SellSeeds>();
		List<Object>param = new ArrayList<Object>();
		
		if (!text.isEmpty()||text!=null) {
			sb.append("where d.sellseedsname like ? ");
			param.add(text.concat("%"));
		}
				
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(new String(sb))) {
			for (int i = 0; i < param.size(); i++) {
				stmt.setObject(i+1, param.get(i));
				
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SellSeeds sellseeds = new SellSeeds();
				sellseeds.setSellseedsdate(rs.getTimestamp("sellseedsdate").toLocalDateTime());
				sellseeds.setSellseedsname(rs.getString("sellseedsname"));
				sellseeds.setSellseedscategory(rs.getString("sellseedscategory"));
				sellseeds.setSellseedstotal(rs.getString("sellseedstotal"));
				sellseeds.setSellseedsoneperprice(rs.getString("sellseedsoneperprice"));
				sellseeds.setSellseedsaddress(rs.getString("sellseedsaddress"));
				sellseeds.setSellseedstotalprice(rs.getString("sellseedstotalprice"));
				list.add(sellseeds);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		// TODO Auto-generated method stub
		
	}
	public void delete(SellSeeds sellseeds) {
		String sql = "delete from sellseeds where sellseedsname=?";
		try(Connection con = DatabaseManager.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, sellseeds.getSellseedsname());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
