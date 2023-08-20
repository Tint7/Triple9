package com.solt.triplenine.entity;

import java.time.LocalDateTime;

public class StoreList {
	private LocalDateTime stlistdate;
	private String stlistname;
	private String stlistphno;
	private String stlistaddress;
	private String stlistnumber;
	public String getStlistname() {
		return stlistname;
	}
	public void setStlistname(String stlistname) {
		this.stlistname = stlistname;
	}
	public String getStlistphno() {
		return stlistphno;
	}
	public void setStlistphno(String stlistphno) {
		this.stlistphno = stlistphno;
	}
	public String getStlistaddress() {
		return stlistaddress;
	}
	public void setStlistaddress(String stlistaddress) {
		this.stlistaddress = stlistaddress;
	}
	public String getStlistnumber() {
		return stlistnumber;
	}
	public void setStlistnumber(String stlistnumber) {
		this.stlistnumber = stlistnumber;
	}
	public LocalDateTime getStlistdate() {
		return stlistdate;
	}
	public void setStlistdate(LocalDateTime stlistdate) {
		this.stlistdate = stlistdate;
	}
	
}
