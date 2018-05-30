package org.fyp.ems.questionSettingModule.EmsDbModel;

import java.util.Date;

public class RejectedQuestion {
	
	private int id;
	private int question_id;
	private String approver_id;
	private String status;
	private String approver_comment;
	private Date rejected_date;
	
	public RejectedQuestion() {
		super();
	}
	public RejectedQuestion(int id, int question_id, String approver_id, String status, String approver_comment,
			Date rejected_date) {
		super();
		this.id = id;
		this.question_id = question_id;
		this.approver_id = approver_id;
		this.status = status;
		this.approver_comment = approver_comment;
		this.rejected_date = rejected_date;
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
	public String getApprover_id() {
		return approver_id;
	}
	public void setApprover_id(String approver_id) {
		this.approver_id = approver_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApprover_comment() {
		return approver_comment;
	}
	public void setApprover_comment(String approver_comment) {
		this.approver_comment = approver_comment;
	}
	public Date getRejected_date() {
		return rejected_date;
	}
	public void setRejected_date(java.util.Date date) {
		this.rejected_date = date;
	}
	
	
	

}
