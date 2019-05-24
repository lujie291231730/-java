package com.seed.entity.publics;

public class DataDetail {

	private String users;
	private String details;
	private String deletes;
	private String files;

	public DataDetail() {
		this.OnInit();
	}

	public void OnInit() {
		this.users = "";
		this.details = "";
		this.deletes = "";
		this.files = "";
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDeletes() {
		return deletes;
	}

	public void setDeletes(String deletes) {
		this.deletes = deletes;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
}
