package org.fyp.ems.questionSettingModule.EmsDbModel;

public class Batches {

	private int id;
	private String name;
	
	public Batches() {
		super();
	}
	public Batches(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
}
