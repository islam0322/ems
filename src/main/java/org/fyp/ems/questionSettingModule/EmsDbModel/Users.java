package org.fyp.ems.questionSettingModule.EmsDbModel;

import java.util.Date;

public class Users {
	
	private int id;
	private String username;
	private String password;
	private int role;
	private String status;
	private String salt;
	private String name;
	private Date  joining_date;
	private String parent_id;
	
	public Users() {
		
	}
	public Users(int id, String user_name, String password, int role, String status, String salt, String name,
			Date joining_date, String parent_id) {
		super();
		this.id = id;
		this.username = user_name;
		this.password = password;
		this.role = role;
		this.status = status;
		this.salt = salt;
		this.name = name;
		this.joining_date = joining_date;
		this.parent_id = parent_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getJoining_date() {
		return joining_date;
	}
	public void setJoining_date(Date joining_date) {
		this.joining_date = joining_date;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	
	
	
}
