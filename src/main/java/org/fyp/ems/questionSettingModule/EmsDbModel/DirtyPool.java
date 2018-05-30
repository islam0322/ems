package org.fyp.ems.questionSettingModule.EmsDbModel;

import java.util.Date;

public class DirtyPool {
	
	private int id;
	private int question_id;
	private Date pending_date;
	
	public DirtyPool() {
		
	}

	public DirtyPool(int id,int question_id, Date pending_date) {
		super();
		this.id = id;
		this.question_id = question_id;
		this.pending_date = pending_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public Date getPending_date() {
		return pending_date;
	}

	public void setPending_date(Date pending_date) {
		this.pending_date = pending_date;
	}

	
}
