package com.cakeon.board.model;

import java.util.Date;

public class BoardFileInfoDTO {

	private int id;
	private int article_id;
	private String saveFolder;
	private String originFile;
	private String saveFile;
	private String ext;
	private String ip;
	private Date regidate;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getArticle_id() {
		return article_id;
	}
	
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	
	public String getSaveFolder() {
		return saveFolder;
	}
	
	public void setSaveFolder(String saveFolder) {
		this.saveFolder = saveFolder;
	}
	
	public String getOriginFile() {
		return originFile;
	}
	
	public void setOriginFile(String originFile) {
		this.originFile = originFile;
	}
	public String getSaveFile() {
		return saveFile;
	}
	
	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}
	
	public String getExt() {
		return ext;
	}
	
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getRegidate() {
		return regidate;
	}
	
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	
}
