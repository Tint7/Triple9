package com.solt.triplenine.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.solt.triplenine.entity.ExportSeeds;
import com.solt.triplenine.util.DatabaseManager;

public class ExportSeedsService {
	public void add(ExportSeeds expseeds) {
		String sql = "insert into exportseeds (expseedsdate,expseedsname,expseedsnrcno,expseedscategory,expseedstotal,expseedscarno,expseedsphno)values(?,?,?,?,?,?,?);";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
				stmt.setString(2, expseeds.getExpseedsname());
				stmt.setString(3, expseeds.getExpseedsnrcno());
				stmt.setString(4, expseeds.getExpseedscategory());
				stmt.setString(5, expseeds.getExpseedstotal());
				stmt.setString(6, expseeds.getExpseedscarno());
				stmt.setString(7, expseeds.getExpseedsphno());
				stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<ExportSeeds>findAll() {
		// TODO Auto-generated method stub
		List<ExportSeeds>list = new ArrayList<ExportSeeds>();
		String sql = "select * from exportseeds";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ExportSeeds expseeds = new ExportSeeds();
				expseeds.setExpseedsdate(rs.getTimestamp("expseedsdate").toLocalDateTime());
				expseeds.setExpseedsname(rs.getString("expseedsname"));
				expseeds.setExpseedsnrcno(rs.getString("expseedsnrcno"));
				expseeds.setExpseedscategory(rs.getString("expseedscategory"));
				expseeds.setExpseedstotal(rs.getString("expseedstotal"));
				expseeds.setExpseedscarno(rs.getString("expseedscarno"));
				expseeds.setExpseedsphno(rs.getString("expseedsphno"));
				list.add(expseeds);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public List<ExportSeeds>find(String text) {
		StringBuffer sb = new StringBuffer("Select * from exportseeds e ");
		List<ExportSeeds>list = new ArrayList<ExportSeeds>();
		List<Object>param = new ArrayList<Object>();
		
		if (!text.isEmpty()||text!=null) {
			sb.append("where e.expseedsname like ? ");
			param.add(text.concat("%"));
		}
				
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(new String(sb))) {
			for (int i = 0; i < param.size(); i++) {
				stmt.setObject(i+1, param.get(i));
				
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ExportSeeds expseeds = new ExportSeeds();
				expseeds.setExpseedsdate(rs.getTimestamp("expseedsdate").toLocalDateTime());
				expseeds.setExpseedsname(rs.getString("expseedsname"));
				expseeds.setExpseedsnrcno(rs.getString("expseedsnrcno"));
				expseeds.setExpseedscategory(rs.getString("expseedscategory"));
				expseeds.setExpseedstotal(rs.getString("expseedstotal"));
				expseeds.setExpseedscarno(rs.getString("expseedscarno"));
				expseeds.setExpseedsphno(rs.getString("expseedsphno"));
				list.add(expseeds);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		// TODO Auto-generated method stub
		
	}
	public void delete(ExportSeeds expseeds) {
		String sql = "delete from exportseeds where expseedsnrcno=?";
		try(Connection con = DatabaseManager.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, expseeds.getExpseedsnrcno());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
