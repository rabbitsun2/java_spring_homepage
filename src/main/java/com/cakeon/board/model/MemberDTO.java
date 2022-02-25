package com.cakeon.board.model;

import java.util.Date;

public class MemberDTO {

	private int id;
	private String userid;
	private String passwd;
	private String name;
	private String email;
	private int remember;
	private int is_enabled;
	private Date regidate;
	private String ip;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getRemember() {
		return remember;
	}
	
	public void setRemember(int remember) {
		this.remember = remember;
	}
	
	public int getIs_enabled() {
		return is_enabled;
	}
	
	public void setIs_enabled(int is_enabled) {
		this.is_enabled = is_enabled;
	}
	
	public Date getRegidate() {
		return regidate;
	}
	
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
