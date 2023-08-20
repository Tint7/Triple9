package com.solt.triplenine.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.solt.triplenine.entity.Member;
import com.solt.triplenine.util.DatabaseManager;

public class MemberService {

	public Member findLoginname(String name) {
		String sql = "select * from member where mname=?";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setMname(rs.getString("mname"));
				member.setMposition(rs.getString("mposition"));
				member.setMphno(rs.getString("mphno"));
				member.setMpassword(rs.getString("mpassword"));
				return member;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public void add(Member member) {
		String sql = "insert into member (mname,mposition,mphno,mpassword)values(?,?,?,?);";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, member.getMname());
				stmt.setString(2, member.getMposition());
				stmt.setString(3, member.getMphno());
				stmt.setString(4, member.getMpassword());
				stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<Member>findAll() {
		// TODO Auto-generated method stub
		List<Member>list = new ArrayList<Member>();
		String sql = "select * from member";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setMname(rs.getString("mname"));
				member.setMposition(rs.getString("mposition"));
				member.setMphno(rs.getString("mphno"));
				member.setMpassword(rs.getString("mpassword"));
				list.add(member);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public List<Member> find(String text) {
		StringBuffer sb = new StringBuffer("Select * from member m ");
		List<Member>list = new ArrayList<Member>();
		List<Object>param = new ArrayList<Object>();
		
		if (!text.isEmpty()||text!=null) {
			sb.append("where m.mname like ? ");
			param.add(text.concat("%"));
		}
				
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(new String(sb))) {
			for (int i = 0; i < param.size(); i++) {
				stmt.setObject(i+1, param.get(i));
				
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setMname(rs.getString("mname"));
				member.setMposition(rs.getString("mposition"));
				member.setMphno(rs.getString("mphno"));
				member.setMpassword(rs.getString("mpassword"));
				list.add(member);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		// TODO Auto-generated method stub
		
	}
	public void delete(Member member) {
		String sql = "delete from member where mposition=?";
		try(Connection con = DatabaseManager.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, member.getMposition());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void update(Member member) {
		// TODO Auto-generated method stub
		String sql = "update member set mname=?,mphno=?,mpassword=? where mposition=?";
		try(Connection con = DatabaseManager.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, member.getMname());
			stmt.setString(2, member.getMphno());
			stmt.setString(3, member.getMpassword());
			stmt.setString(4, member.getMposition());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
