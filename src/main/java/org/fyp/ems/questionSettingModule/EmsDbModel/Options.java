package org.fyp.ems.questionSettingModule.EmsDbModel;

public class Options {

	private int id;
	private int question_id;
	private String option_statment;
	private String correct_status;
	private String  image_status;
 
	public Options() {
		super();
	}
	
	public Options(int id, int question_id, String option_statment, String status, String  image) {
		super();
		this.id = id;
		this.question_id = question_id;
		this.option_statment = option_statment;
		this.correct_status = status;
		this.image_status = image;
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
	public String getOption_statment() {
		return option_statment;
	}
	public void setOption_statment(String option_statment) {
		this.option_statment = option_statment;
	}
	
	public String getCorrect_status() {
		return correct_status;
	}

	public void setCorrect_status(String correct_status) {
		this.correct_status = correct_status;
	}

	public String getImage_status() {
		return image_status;
	}

	public void setImage_status(String image_status) {
		this.image_status = image_status;
	}
	
	
}
