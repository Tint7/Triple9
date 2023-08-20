package com.solt.triplenine.entity;

import java.time.LocalDateTime;

public class Category {
	private LocalDateTime cdate;
	private String cname;
	private String coneperprice;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getConeperprice() {
		return coneperprice;
	}
	public void setConeperprice(String coneperprice) {
		this.coneperprice = coneperprice;
	}
	public LocalDateTime getCdate() {
		return cdate;
	}
	public void setCdate(LocalDateTime cdate) {
		this.cdate = cdate;
	}
	

}
