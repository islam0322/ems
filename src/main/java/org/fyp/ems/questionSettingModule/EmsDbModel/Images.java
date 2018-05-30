package org.fyp.ems.questionSettingModule.EmsDbModel;

public class Images {

	private int id;
	private byte [] image;
	private int question_id;
	private int option_id;
	public Images() {
		super();
	}
	public Images(int id, byte[] image, int question_id, int option_id) {
		super();
		this.id = id;
		this.image = image;
		this.question_id = question_id;
		this.option_id = option_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getOption_id() {
		return option_id;
	}
	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}
	
}
