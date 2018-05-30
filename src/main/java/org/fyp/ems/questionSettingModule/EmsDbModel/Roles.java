package org.fyp.ems.questionSettingModule.EmsDbModel;

import java.util.Date;

public class Roles {
	
	private int id;
	private String name;
	private Date  created_date;
	
	public Roles() {
		super();
	}
	public Roles(int id, String name, Date crated_date) {
		super();
		this.id = id;
		this.name = name;
		this.created_date = crated_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	
	

}
