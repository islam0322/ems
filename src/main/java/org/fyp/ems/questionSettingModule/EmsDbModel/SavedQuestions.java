package org.fyp.ems.questionSettingModule.EmsDbModel;

import java.util.Date;

public class SavedQuestions {

	private int id;
	private int question_id;
	private Date saved_date;
	
	public SavedQuestions() {
		super();
	}
	public SavedQuestions(int question_id, Date saved_date) {
		super();
		this.question_id = question_id;
		this.saved_date = saved_date;
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
	public Date getSaved_date() {
		return saved_date;
	}
	public void setSaved_date(Date saved_date) {
		this.saved_date = saved_date;
	}
	
	
	
	
	
}
