package org.fyp.ems.questionSettingModule.EmsDbModel;

import java.util.Date;

public class Questions {
	
	private int id;
	private String statement;
	private String statement_image;
	private int estimated_time;
	private String subject;
	private String topic;
	private String sub_Topic;
	private int difficulty_level;
	private int marks;
	private String comment;
	private String auther;
	private Date creation_date;
	private String status;
	private String editor_id;
	private Date edited_date;
	
	public Questions() {
		
	}

	public Questions(int id, String statement, String statement_image, int estimated_time, String subject, String topic,
			String sub_Topic, int difficulty_level, int marks, String comment, String auther, Date creation_date,
			String status, String editor_id, Date edited_date) {
		super();
		this.id = id;
		this.statement = statement;
		this.statement_image = statement_image;
		this.estimated_time = estimated_time;
		this.subject = subject;
		this.topic = topic;
		this.sub_Topic = sub_Topic;
		this.difficulty_level = difficulty_level;
		this.marks = marks;
		this.comment = comment;
		this.auther = auther;
		this.creation_date = creation_date;
		this.status = status;
		this.editor_id = editor_id;
		this.edited_date = edited_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getStatement_image() {
		return statement_image;
	}

	public void setStatement_image(String statement_image) {
		this.statement_image = statement_image;
	}

	public int getEstimated_time() {
		return estimated_time;
	}

	public void setEstimated_time(int estimated_time) {
		this.estimated_time = estimated_time;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getSub_Topic() {
		return sub_Topic;
	}

	public void setSub_Topic(String sub_Topic) {
		this.sub_Topic = sub_Topic;
	}

	public int getDifficulty_level() {
		return difficulty_level;
	}

	public void setDifficulty_level(int difficulty_level) {
		this.difficulty_level = difficulty_level;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEditor_id() {
		return editor_id;
	}

	public void setEditor_id(String editor_id) {
		this.editor_id = editor_id;
	}

	public Date getEdited_date() {
		return edited_date;
	}

	public void setEdited_date(Date edited_date) {
		this.edited_date = edited_date;
	}

	
}